package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitController {
    private final UserService userService;
    private final RoleService roleService;

    public InitController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public Role injectRole() {
        Role role = new Role();
        role.setRoleName("USER");
        roleService.add(role);
        return role;
    }

    @PostConstruct
    public User injectUser() {
        User user = new User();
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.getRoles().add(roleService.getByName("USER"));
        userService.add(user);
        return user;
    }

}
