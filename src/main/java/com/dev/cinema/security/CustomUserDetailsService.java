package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByEmail(s);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(s);
            builder.password(user.getPassword());
            builder.roles(mapRoles(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

    private String[] mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList())
                .toArray(String[]::new);
    }
}
