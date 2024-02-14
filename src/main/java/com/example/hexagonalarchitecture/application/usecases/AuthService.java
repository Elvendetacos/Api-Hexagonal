package com.example.hexagonalarchitecture.application.usecases;

import com.example.hexagonalarchitecture.domain.model.dto.JwtResponse;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.JwtRequest;
import com.example.hexagonalarchitecture.domain.model.dto.request.SignUpRequest;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;

public interface AuthService {
    JwtResponse signIn(JwtRequest request);
    UserDto signUp(UserRequest request);
    String verify(String code);
}
