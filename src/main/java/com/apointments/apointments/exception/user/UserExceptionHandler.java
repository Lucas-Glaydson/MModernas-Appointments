package com.apointments.apointments.exception.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmail(EmailAlreadyExistsException ex){
        return ResponseEntity.status(409).body(ex.getMessage());
    }


}
