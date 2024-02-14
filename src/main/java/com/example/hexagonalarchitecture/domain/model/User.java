package com.example.hexagonalarchitecture.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String code;
    private Boolean verified;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.code = UUID.randomUUID().toString();
        this.verified = false;
    }

}
