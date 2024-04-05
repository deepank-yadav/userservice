package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.LoginRequestDto;
import com.scaler.userservice.dtos.LogoutRequestDto;
import com.scaler.userservice.dtos.SignUpRequestDto;
import com.scaler.userservice.dtos.UserDto;
import com.scaler.userservice.exceptions.UserNotExistException;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.TokenRepository;
import com.scaler.userservice.services.UserService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserController(UserService userService,
                          TokenRepository tokenRepository){
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto){
        return userService.login(requestDto.getEmail(), requestDto.getPassword());
    }


    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable("id") Long id) throws UserNotExistException {
        return userService.getSingleUser(id);
    }

    @GetMapping
    public List<User> getAllUser() throws UserNotExistException {
        return userService.getAllUser();
    }

    @PostMapping
    public User addUser(@RequestBody User user)                                                                                                                                                                                                                                                                                                                                    {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) throws UserNotExistException {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") Long id) throws UserNotExistException {
        return userService.deleteUser(id);
    }
    @PostMapping("/signup")

    public UserDto signUp(@RequestBody SignUpRequestDto request){
        String email =  request.getEmail();
        String password = request.getPassword();
        String name = request.getName();

        return UserDto.from(userService.signUp(name,email,password));
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request){
        userService.logout(request.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NotNull String token){
        return UserDto.from(userService.validateToken(token));
    }

//    @PostMapping("/validate/{token}")
//    public User validateToken(@PathVariable("token") @NotNull String token){
//        return userService.validateToken(token);
//    }
}
