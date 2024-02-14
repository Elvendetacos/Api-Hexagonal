package com.example.hexagonalarchitecture.application.usecases;


import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;

import java.util.List;

public interface UserService {
    UserDto createNew(UserRequest request);
    UserDto getById(String id);
    UserDto getByEmail(String email);
    User getUsername(String email);
    List<UserDto> getAll();
    void deleteById(String id);
    UserDto update(UserRequest request, String id);
}
