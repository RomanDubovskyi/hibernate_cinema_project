package com.dev.cinema.model.dto;

import com.dev.cinema.annotations.EmailValidation;
import com.dev.cinema.annotations.PasswordValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordValidation
public class UserRegistrationDto {
    @NotNull
    @EmailValidation
    private String email;
    @NotNull
    @Size(min = 4, max = 12)
    private String password;
    @NotNull
    @Size(min = 4, max = 12)
    private String repeatPassword;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
