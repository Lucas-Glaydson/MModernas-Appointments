package com.apointments.apointments.exception.user;

import com.apointments.apointments.exception.BusinessException;

public class CpfAlreadyExistsException extends BusinessException {
    public CpfAlreadyExistsException(String cpf) {
        super("CPF already exists: " + cpf);
    }
}
