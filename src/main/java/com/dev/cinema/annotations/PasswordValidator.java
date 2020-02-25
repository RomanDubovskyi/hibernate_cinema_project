package com.dev.cinema.annotations;

import com.dev.cinema.model.dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordValidation, UserRegistrationDto> {
    @Override
    public boolean isValid(UserRegistrationDto user,
                           ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword().equals(user.getRepeatPassword());
    }
}
