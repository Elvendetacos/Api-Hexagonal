package com.example.hexagonalarchitecture.application.service;

import com.example.hexagonalarchitecture.application.mapper.UserDtoMapper;
import com.example.hexagonalarchitecture.application.mapper.UserRequestMapper;
import com.example.hexagonalarchitecture.application.usecases.UserService;
import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;
import com.example.hexagonalarchitecture.domain.port.UserPersistencePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementService implements UserService {

    private final UserPersistencePort userPersistencePort;
    private final UserRequestMapper userRequestMapper;
    private final UserDtoMapper userDtoMapper;

    public UserManagementService(final UserPersistencePort userPersistencePort,
                                 final UserRequestMapper userRequestMapper,
                                 final UserDtoMapper userDtoMapper) {
        this.userPersistencePort = userPersistencePort;
        this.userRequestMapper = userRequestMapper;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public UserDto createNew(UserRequest request) {
        var userRequest = userRequestMapper.toDomain(request);
        userRequest.setCreatedAt(LocalDateTime.now());
        var userCreated = userPersistencePort.create(userRequest);
        return userDtoMapper.toDto(userCreated);
    }

    @Override
    public UserDto getById(String id) {
        var user = userPersistencePort.getById(id);
        return userDtoMapper.toDto(user);
    }

    @Override
    public UserDto getByEmail(String email) {
        var user = userPersistencePort.getByEmail(email);
        return userDtoMapper.toDto(user);
    }

    @Override
    public User getUsername(String email) {
        return userPersistencePort.getByEmail(email);
    }

    @Override
    public List<UserDto> getAll() {
        var users = userPersistencePort.getAll();
        return users
                .stream()
                .map(userDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        var user = userPersistencePort.getById(id);
        user.setDeletedAt(LocalDateTime.now());
        userPersistencePort.deleteById(id);
    }

    @Override
    public UserDto update(UserRequest request, String id) {
        var userToUpdate = userRequestMapper.toDomain(request);
        var userUpdated = userPersistencePort.update(userToUpdate, id);

        return userDtoMapper.toDto(userUpdated);
    }


}
