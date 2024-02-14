package com.example.hexagonalarchitecture.domain.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    private String email;

    private String password;

}
