package com.apointments.apointments.features.auth.controller;

import com.apointments.apointments.features.auth.dto.CreateUserRequest;
import com.apointments.apointments.features.auth.dto.UserResponse;
import com.apointments.apointments.features.auth.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping()
    public ResponseEntity<UserResponse> register(@Valid @RequestBody CreateUserRequest request){
        UserResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
