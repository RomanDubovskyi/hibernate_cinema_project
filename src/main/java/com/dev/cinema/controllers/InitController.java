package com.dev.cinema.controllers;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class InitController {
    private final UserService userService;
    private final RoleDao roleDao;

    public InitController(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @PostConstruct
    public Role injectRole() {
        Role role = new Role();
        role.setRoleName("USER");
        roleDao.add(role);
        return role;
    }

    @PostConstruct
    public User injectUser() {
        User user = new User();
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.getRoles().add(roleDao.getByName("USER"));
        userService.add(user);
        return user;
    }

}
