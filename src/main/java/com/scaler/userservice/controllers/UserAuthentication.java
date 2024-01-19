package com.scaler.userservice.controllers;

import com.scaler.userservice.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthentication {

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        return "token";
    }
}
