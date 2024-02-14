package com.example.hexagonalarchitecture.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private String phone;

}
