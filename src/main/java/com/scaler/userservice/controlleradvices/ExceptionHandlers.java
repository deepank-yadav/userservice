package com.scaler.userservice.controlleradvices;

import com.scaler.userservice.dtos.ExceptionDto;
import com.scaler.userservice.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ExceptionDto> handleUserNotExistException(UserNotExistException exception){
        ExceptionDto dto = new ExceptionDto();

        dto.setMessage(exception.getMessage());
        dto.setDetail("Please verify the id");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
