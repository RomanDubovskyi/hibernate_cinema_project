package com.dev.cinema.service.impl;

import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

import javax.security.sasl.AuthenticationException;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public AuthenticationServiceImpl(ShoppingCartService shoppingCartService,
                                     UserService userService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user == null || !HashUtil.hashPassword(
                password, user.getSalt()).equals(user.getPassword())) {
            throw new AuthenticationException("Login or password is incorrect!");
        }
        return user;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
