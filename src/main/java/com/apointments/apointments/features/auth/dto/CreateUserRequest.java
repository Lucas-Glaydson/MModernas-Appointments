package com.apointments.apointments.features.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CreateUserRequest(
        @NotEmpty
        String firstName,

        @NotEmpty
        String lastName,

        @Email  @NotEmpty
        String email,

        @Size(min = 6)
        String password,

        @Size(min = 11, max = 11)
        String cpf,

        Date birthday,

        @Min(11) //Format with country code such +55
        String phoneNumber
) {

}
