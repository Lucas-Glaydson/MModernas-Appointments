package com.apointments.apointments.auth;

import com.apointments.apointments.exception.user.CpfAlreadyExistsException;
import com.apointments.apointments.exception.user.EmailAlreadyExistsException;
import com.apointments.apointments.features.auth.dto.CreateUserRequest;
import com.apointments.apointments.features.auth.dto.UserResponse;
import com.apointments.apointments.features.auth.service.AuthService;
import com.apointments.apointments.features.user.factory.UserFactory;
import com.apointments.apointments.features.user.mapper.UserMapper;
import com.apointments.apointments.features.user.model.Role;
import com.apointments.apointments.features.user.model.UserModel;
import com.apointments.apointments.features.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterAuthServiceTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserFactory factory;

    @Mock
    private PasswordEncoder passwordEncoder;

    private CreateUserRequest request;

    private Date birthDate;

    @BeforeEach
    void setup(){
        birthDate = new Date(946684800000L);

        request = new CreateUserRequest(
                "John",
                "Doe",
                "jhonDoe@email.com",
                "12341234g1",
                "00000000000",
                birthDate,
                "+5581900000000"
            );
    }

    @Test
    void shouldRegisterUserSuccessfully(){
        String hashedPassword = "hashedPassword";

        UserModel userModel = new UserModel();
        UserModel savedUser = new UserModel();
        UserResponse response = new UserResponse(
                UUID.randomUUID(),
                "John",
                "Doe",
                "johnDoe@email.com",
                "00000000000",
                birthDate,
                "+5581900000000"
        );

        when(userRepository.findOneByEmail(request.email())).thenReturn(null);
        when(userRepository.findOneByCpf(request.cpf())).thenReturn(null);
        when(passwordEncoder.encode(request.password())).thenReturn(hashedPassword);
        when(mapper.toModel(request)).thenReturn(userModel);
        when(factory.create(userModel, hashedPassword, Role.CLIENT)).thenReturn(userModel);
        when(userRepository.save(userModel)).thenReturn(savedUser);
        when(mapper.modelToResponse(savedUser)).thenReturn(response);

        UserResponse result = authService.register(request);

        assertNotNull(result);
        assertEquals("johnDoe@email.com", result.email());

        verify(userRepository).save(userModel);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists(){
        when(userRepository.findOneByEmail(request.email())).thenReturn(new UserModel());

        assertThrows(EmailAlreadyExistsException.class, () -> {
            authService.register(request);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCpfAlreadyExists(){
        when(userRepository.findOneByEmail(request.email())).thenReturn(null);
        when(userRepository.findOneByCpf(request.cpf())).thenReturn(new UserModel());

        assertThrows(CpfAlreadyExistsException.class, () -> {
            authService.register(request);
        });

        verify(userRepository, never()).save(any());
    }
}
