package com.example.hexagonalarchitecture.infraestructure.rest.controller;

import com.example.hexagonalarchitecture.application.usecases.AuthService;
import com.example.hexagonalarchitecture.domain.model.dto.JwtResponse;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.JwtRequest;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> signIn(@RequestBody JwtRequest request) {

        return new ResponseEntity<>(service.signIn(request), HttpStatus.OK);
    }

    @PostMapping("signUp")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserRequest request) {
        return new ResponseEntity<>(service.signUp(request), HttpStatus.OK);
    }

    @GetMapping("verify")
    public ResponseEntity<String> verify(@RequestParam String code) {
        return new ResponseEntity<>(service.verify(code), HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
