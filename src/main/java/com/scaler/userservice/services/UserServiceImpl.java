package com.scaler.userservice.services;

import com.scaler.userservice.exceptions.UserNotExistException;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        return user;
    }
}
