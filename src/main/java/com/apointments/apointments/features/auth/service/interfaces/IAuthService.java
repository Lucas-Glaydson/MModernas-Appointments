package com.apointments.apointments.features.auth.service.interfaces;

import com.apointments.apointments.features.auth.dto.CreateUserRequest;
import com.apointments.apointments.features.auth.dto.UserResponse;

public interface IAuthService {
    UserResponse register(CreateUserRequest userRequest);
}
