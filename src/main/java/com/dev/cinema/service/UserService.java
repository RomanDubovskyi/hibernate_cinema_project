package com.dev.cinema.service;

import com.dev.cinema.model.User;

public interface UserService {
    User add(User user);

    User getById(Long id);

    User findByEmail(String email);
}
