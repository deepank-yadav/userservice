package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.SignUpRequestDto;
import com.scaler.userservice.exceptions.UserNotExistException;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

//    @GetMapping
//    public Token login(){
//        return null;
//    }
//
//    public User signup(){
//        return null;
//    }
//
//    public User logout(){
//        return null;
//    }
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
    @PostMapping("/")

    public User signUp(@RequestBody SignUpRequestDto request){
        String email =  request.getEmail();
        String password = request.getPassword();
        String name = request.getName();

        return userService.signUp(name,email,password);
    }
}
