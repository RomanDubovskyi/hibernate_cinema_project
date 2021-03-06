package com.dev.cinema.model.dto;

import com.dev.cinema.annotations.EmailValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestDto {
    @NotNull
    @EmailValidation
    private String email;
    @NotNull
    @Size(min = 4, max = 12)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
