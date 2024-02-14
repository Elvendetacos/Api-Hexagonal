package com.example.hexagonalarchitecture.application.service;

import com.example.hexagonalarchitecture.application.usecases.AuthService;
import com.example.hexagonalarchitecture.domain.model.dto.JwtResponse;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.JwtRequest;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;
import com.example.hexagonalarchitecture.domain.port.AuthPort;
import org.springframework.stereotype.Service;

@Service
public class AuthManagementService implements AuthService {
    private final AuthPort authPort;
    private final UserManagementService userManagementService;

    public AuthManagementService(AuthPort authPort, UserManagementService userManagementService) {
        this.authPort = authPort;
        this.userManagementService = userManagementService;
    }

    @Override
    public JwtResponse signIn(JwtRequest request) {
        return authPort.signIn(request);
    }

    @Override
    public UserDto signUp(UserRequest request) {
        return userManagementService.createNew(request);
    }

    @Override
    public String verify(String code) {
        return authPort.verify(code);
    }
}
