package com.example.hexagonalarchitecture.domain.port;

import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.dto.JwtResponse;
import com.example.hexagonalarchitecture.domain.model.dto.request.JwtRequest;

public interface AuthPort {
    JwtResponse signIn(JwtRequest request);
    User signUp(User user);
    String verify(String code);
}
