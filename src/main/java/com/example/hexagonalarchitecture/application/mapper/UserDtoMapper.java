package com.example.hexagonalarchitecture.application.mapper;

import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring") 
public interface UserDtoMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    UserDto toDto(User domain);

}
