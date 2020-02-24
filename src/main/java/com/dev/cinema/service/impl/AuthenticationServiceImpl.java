package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(ShoppingCartService shoppingCartService,
                                     UserService userService, RoleDao roleDao,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user == null || !passwordEncoder.encode(password).equals(user.getPassword())) {
            throw new AuthenticationException("Login or password is incorrect!");
        }
        return user;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.getRoles().add(roleDao.getByName("USER"));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
