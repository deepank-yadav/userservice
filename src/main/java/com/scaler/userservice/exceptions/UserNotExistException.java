package com.scaler.userservice.exceptions;

public class UserNotExistException extends Exception{

    public UserNotExistException(String message){
        super(message);
    }
}
