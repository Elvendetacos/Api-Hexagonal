package com.example.hexagonalarchitecture.infraestructure.adapter.mapper;

import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.infraestructure.adapter.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserDboMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "verified", target = "verified")
    @Mapping(source = "createdAt", target = "createdAt")
    UserEntity toDbo(User domain);

    @InheritInverseConfiguration
    User toDomain(UserEntity entity);
}