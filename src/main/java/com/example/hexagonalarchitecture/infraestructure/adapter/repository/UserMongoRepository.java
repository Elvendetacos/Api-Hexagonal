package com.example.hexagonalarchitecture.infraestructure.adapter.repository;

import com.example.hexagonalarchitecture.infraestructure.adapter.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByCode(String code);
}
