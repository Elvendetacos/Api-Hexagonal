package com.example.hexagonalarchitecture.infraestructure.adapter;

import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.constant.UserConstant;
import com.example.hexagonalarchitecture.domain.model.dto.JwtResponse;
import com.example.hexagonalarchitecture.domain.model.dto.request.JwtRequest;
import com.example.hexagonalarchitecture.domain.port.AuthPort;
import com.example.hexagonalarchitecture.domain.port.UserPersistencePort;
import com.example.hexagonalarchitecture.infraestructure.adapter.exception.UserException;
import com.example.hexagonalarchitecture.infraestructure.adapter.security.JwtHelper;
import com.example.hexagonalarchitecture.infraestructure.adapter.security.credentials.CredentialDetailsServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthSpringAdapter implements AuthPort {
    @Autowired
    private CredentialDetailsServiceImpls userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;
    @Autowired
    private UserPersistencePort userService;

    public JwtResponse signIn(JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        if (userDetails.isEnabled()){
            String token = this.helper.generateToken(userDetails);
            return toJwtResponse(token, userDetails.getUsername());
        }
        throw new UserException(HttpStatus.NOT_FOUND,
                String.format(UserConstant.USER_NOT_FOUND_MESSAGE_ERROR, userDetails.getUsername()));
    }

    @Override
    public String verify(String code) {
        return userService.verify(code);
    }

    @Override
    public User signUp(User request) {
        return userService.create(request);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!" );
        }

    }

    private JwtResponse toJwtResponse(String token, String username){
        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setUsername(username);

        return response;
    }
}
