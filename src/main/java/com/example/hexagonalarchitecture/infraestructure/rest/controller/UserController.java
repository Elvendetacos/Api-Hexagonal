package com.example.hexagonalarchitecture.infraestructure.rest.controller;

import com.example.hexagonalarchitecture.application.usecases.UserService;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable String id){
        return userService.getById(id);
    }
    @GetMapping("/email/{email}")
    public UserDto getByEmail(@PathVariable String email){
        return userService.getByEmail(email);
    }
    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping()
    public UserDto create(@RequestBody UserRequest taskRequest){
        return userService.createNew(taskRequest);
    }

    @PutMapping("/{id}")
    public UserDto userEdit(@RequestBody UserRequest taskRequest,
                               @PathVariable String id){
        return userService.update(taskRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        userService.deleteById(id);
    }
}
