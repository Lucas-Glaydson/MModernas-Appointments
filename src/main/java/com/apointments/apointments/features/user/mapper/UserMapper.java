package com.apointments.apointments.features.user.mapper;

import com.apointments.apointments.features.auth.dto.CreateUserRequest;
import com.apointments.apointments.features.user.model.Role;
import com.apointments.apointments.features.user.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public UserModel toModel(CreateUserRequest request) {
        return UserModel.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .cpf(request.cpf())
                .birthday(request.birthday())
                .phoneNumber(request.phoneNumber())
                .build();
    }
}
