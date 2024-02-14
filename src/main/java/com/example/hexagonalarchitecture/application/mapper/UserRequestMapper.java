package com.example.hexagonalarchitecture.application.mapper;

import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring") 
public interface UserRequestMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phone", target = "phone")
    User toDomain(UserRequest request);
}
