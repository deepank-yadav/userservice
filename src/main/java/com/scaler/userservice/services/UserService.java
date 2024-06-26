package com.scaler.userservice.services;

import com.scaler.userservice.exceptions.UserNotExistException;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;

import java.util.List;

public interface UserService {

    User getSingleUser(Long id) throws UserNotExistException;

    List<User> getAllUser() throws UserNotExistException;

    User addUser(User user);

    User deleteUser(Long id) throws UserNotExistException;

    User updateUser(Long id, User user) throws UserNotExistException;

    User signUp(String fullName,
                       String email,
                       String password);

    Token login(String email, String password);
    void logout(String token);

    User validateToken(String token);

}
