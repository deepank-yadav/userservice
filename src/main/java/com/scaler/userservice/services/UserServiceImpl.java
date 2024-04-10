package com.scaler.userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.userservice.dtos.SendEmailEventDto;
import com.scaler.userservice.exceptions.UserNotExistException;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.TokenRepository;
import com.scaler.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           TokenRepository tokenRepository,
                           KafkaTemplate<String, String> kafkaTemplate,
                           ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.objectMapper =  objectMapper;
    }
    @Override
    public User getSingleUser(Long id) throws UserNotExistException {

        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotExistException("User not Found with id:"+id);
        }
        User user = userOptional.get();
        return user;
    }

    @Override
    public List<User> getAllUser() throws UserNotExistException {
        Optional<List<User>> userOptional = Optional.of(userRepository.findAll());
        if(userOptional.isEmpty()){
            throw new UserNotExistException("No user found in the system");
        }
        List<User> user = userOptional.get();
        return user;
    }

    @Override
    public User addUser(User user) {
        user.setDeleted(false);
        user.setCreatedAt(new Date());
        user.setLastUpdated(new Date());
        return userRepository.save(user);
    }



    @Override
    public User deleteUser(Long id) throws UserNotExistException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotExistException("User not Found with id:"+id);
        }
        User user = userOptional.get();
        user.setDeleted(true);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User u) throws UserNotExistException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotExistException("User not Found with id:"+id);
        }
        User user = userOptional.get();
        return userRepository.save(user);
    }

    @Override
    public User signUp(String fullName, String email, String password) {

        User u = new User();

        u.setName(fullName);
        u.setEmail(email);
        u.setHashPassword(bCryptPasswordEncoder.encode(password));
        User user = userRepository.save(u);

        SendEmailEventDto sendEmailEventDto = new SendEmailEventDto();
        sendEmailEventDto.setTo(email);
        sendEmailEventDto.setFrom("info@test.com");
        sendEmailEventDto.setSubject("Welcome to our platform");
        sendEmailEventDto.setBody("Welcome to our platform. You have successfully signed up with email: "+email);
        try {
            kafkaTemplate.send(
                    "sendEmail",
                    objectMapper.writeValueAsString(sendEmailEventDto)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findAllByEmail(email);
        if(userOptional.isEmpty()){
            //throw password not matched exception
            return null;
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getHashPassword())){
            //throw password not matched exception
            return null;
        }
        Token token = new Token();
        LocalDate currentDate = LocalDate.now();
        LocalDate dateAfter30Days = currentDate.plusDays(30);

        Date expiryDate = Date.from(dateAfter30Days.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setUser(user);
        token.setExpiryAt(expiryDate);

        token.setValue(RandomStringUtils.randomAlphabetic(128));


        return tokenRepository.save(token);
    }
    @Override
    public void logout( String token){

        Optional<Token> token1 = tokenRepository.findByValueAndDeleted(token,false);


        if(token1.isEmpty()){
            //throw TokenNotExistOrAlreadyExpiredException
            return;
        }
        Token token2 = token1.get();
        token2.setDeleted(true);
        tokenRepository.save(token2);
    }

    @Override
    public User validateToken(String token){
        Optional<Token> token1= tokenRepository.findByValueAndDeletedEqualsAndExpiryAtGreaterThan(token,false, new Date());
        if(token1.isEmpty()){
            //throw TokenNotExistOrAlreadyExpiredException
            return null;
        }
        return token1.get().getUser();
    }
}
