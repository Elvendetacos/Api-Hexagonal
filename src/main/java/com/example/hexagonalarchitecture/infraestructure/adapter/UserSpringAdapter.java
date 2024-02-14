package com.example.hexagonalarchitecture.infraestructure.adapter;

import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.constant.UserConstant;
import com.example.hexagonalarchitecture.domain.port.UserPersistencePort;
import com.example.hexagonalarchitecture.infraestructure.adapter.entity.UserEntity;
import com.example.hexagonalarchitecture.infraestructure.adapter.exception.UserException;
import com.example.hexagonalarchitecture.infraestructure.adapter.mapper.UserDboMapper;
import com.example.hexagonalarchitecture.infraestructure.adapter.repository.UserJpaRepository;
import com.example.hexagonalarchitecture.infraestructure.mail.EmailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserSpringAdapter implements UserPersistencePort {
    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userRepository;
    private final UserDboMapper userDboMapper;
    private final EmailSenderService emailSenderService;

    @Value("${link.verify}")
    private String link;

    public UserSpringAdapter(PasswordEncoder passwordEncoder, UserJpaRepository userRepository, UserDboMapper userDboMapper, EmailSenderService emailSenderService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userDboMapper = userDboMapper;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public User create(User user) {

        var userToSave = userDboMapper.toDbo(user);
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        var userSaved = userRepository.save(userToSave);
        if (userSaved != null) {
            emailSenderService.sendSimpleEmail(userSaved.getEmail(), UserConstant.SUBJECT_MAIL,
                    UserConstant.BODY_MAIL + link + userSaved.getCode());
        }
        return userDboMapper.toDomain(userSaved);
    }

    @Override
    public User getById(String id) {

        var optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new UserException(HttpStatus.NOT_FOUND,
                    String.format(UserConstant.USER_NOT_FOUND_MESSAGE_ERROR, id));
        }

        return userDboMapper.toDomain(optionalUser.get());
    }

    @Override
    public User getByEmail(String email) {
        var optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()){
            throw new UserException(HttpStatus.NOT_FOUND,
                    String.format(UserConstant.USER_NOT_FOUND_MESSAGE_ERROR, email));
        }
        return userDboMapper.toDomain(optionalUser.get());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userDboMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, String id) {
        var userToUpdate = findAndEnsureExists(id);
        userToUpdate.setId(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setUpdatedAt(LocalDateTime.now());
//        var userToUpdate = userDboMapper.toDbo(user);
        var userUpdated = userRepository.save(userToUpdate);

        return userDboMapper.toDomain(userUpdated);
    }

    @Override
    public String verify(String code){
        UserEntity user = findByCodeAndEnsureExists(code);
        if (user != null){
            user.setVerified(true);
            user.setVerifiedAt(LocalDateTime.now());
            userRepository.save(user);
            return String.format(UserConstant.CODE_VERIFIED, user.getEmail());
        }
        throw new UserException(HttpStatus.NOT_FOUND,
                String.format(UserConstant.CODE_NOT_FOUND_MESSAGE_ERROR, code));

    }

    private UserEntity findAndEnsureExists(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    private UserEntity findByEmailAndEnsureExists(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    private UserEntity findByCodeAndEnsureExists(String code) {
        return userRepository.findByCode(code).orElseThrow();
    }

}
