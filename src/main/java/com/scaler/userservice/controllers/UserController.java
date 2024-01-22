package com.scaler.userservice.controllers;

import com.scaler.userservice.exceptions.UserNotExistException;
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
    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable("id") Long id){
        return null;
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
}
