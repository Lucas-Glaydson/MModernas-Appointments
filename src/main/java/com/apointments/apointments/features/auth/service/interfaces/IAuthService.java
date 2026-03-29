package com.apointments.apointments.features.auth.service.interfaces;

import com.apointments.apointments.features.auth.dto.CreateUserRequest;

public interface IAuthService {
    void register(CreateUserRequest userRequest);
}
