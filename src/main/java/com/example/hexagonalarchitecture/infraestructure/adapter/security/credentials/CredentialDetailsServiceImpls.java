package com.example.hexagonalarchitecture.infraestructure.adapter.security.credentials;

import com.example.hexagonalarchitecture.application.usecases.UserService;
import com.example.hexagonalarchitecture.infraestructure.adapter.entity.UserEntity;
import com.example.hexagonalarchitecture.infraestructure.adapter.mapper.UserDboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialDetailsServiceImpls implements UserDetailsService {

    @Autowired
    private UserService service;

    private final UserDboMapper userDboMapper;

    public CredentialDetailsServiceImpls(UserDboMapper userDboMapper) {
        this.userDboMapper = userDboMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDboMapper.toDbo(service.getUsername(username));
        return new CredentialDetailsImpl(user);
    }

}
