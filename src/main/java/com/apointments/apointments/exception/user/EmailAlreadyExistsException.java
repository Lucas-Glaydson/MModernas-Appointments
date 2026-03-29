package com.apointments.apointments.exception.user;

import com.apointments.apointments.exception.BusinessException;

public class EmailAlreadyExistsException extends BusinessException {

    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email);
    }
}
