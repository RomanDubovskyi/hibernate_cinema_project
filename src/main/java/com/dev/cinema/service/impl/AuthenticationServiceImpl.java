package com.dev.cinema.service.impl;

import com.dev.cinema.annotations.Inject;
import com.dev.cinema.annotations.Service;
import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

import javax.security.sasl.AuthenticationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserDao userDao;
    @Inject
    UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userDao.findByEmail(email);
        if (user == null || !HashUtil.hashPassword(
                password, user.getSalt()).equals(user.getPassword())) {
            throw new AuthenticationException("Login or password is incorrect!");
        }
        return user;
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        if (userDao.findByEmail(email) != null) {
            throw new AuthenticationException("user already exists ");
        }
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        return userService.add(user);
    }
}
