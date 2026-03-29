package com.apointments.apointments.features.auth.service;

import com.apointments.apointments.exception.user.CpfAlreadyExistsException;
import com.apointments.apointments.exception.user.EmailAlreadyExistsException;
import com.apointments.apointments.features.auth.dto.CreateUserRequest;
import com.apointments.apointments.features.auth.dto.UserResponse;
import com.apointments.apointments.features.auth.service.interfaces.IAuthService;
import com.apointments.apointments.features.user.factory.UserFactory;
import com.apointments.apointments.features.user.mapper.UserMapper;
import com.apointments.apointments.features.user.model.Role;
import com.apointments.apointments.features.user.model.UserModel;
import com.apointments.apointments.features.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserFactory factory;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserMapper mapper, UserFactory factory, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.factory = factory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse register(CreateUserRequest userRequest) {
        String passwordHashed = passwordEncoder.encode(userRequest.password());

        if(userRepository.findOneByEmail(userRequest.email()) != null)
            throw new EmailAlreadyExistsException(userRequest.email());

        if(userRepository.findOneByCpf(userRequest.cpf()) != null)
            throw new CpfAlreadyExistsException(userRequest.cpf());

        UserModel newUser = factory.create(mapper.toModel(userRequest), passwordHashed, Role.CLIENT);

        return mapper.modelToResponse(userRepository.save(newUser));
    }
}
