package com.dev.cinema.controllers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public User addUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        User newUser = new User();
        newUser.setEmail(userRequestDto.getEmail());
        newUser.setPassword(userRequestDto.getPassword());
        return userService.add(newUser);
    }

    @GetMapping(value = "/by-email")
    public UserResponseDto getAll(@RequestParam String email) {
        User user = userService.findByEmail(email);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;

    }
}
