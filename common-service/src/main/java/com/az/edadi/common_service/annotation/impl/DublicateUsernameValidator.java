package com.az.edadi.common_service.annotation.impl;


import com.az.edadi.dal.no_sql.repository.UserRepository;
import com.az.edadi.common_service.annotation.NotDuplicateUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class DublicateUsernameValidator implements ConstraintValidator<NotDuplicateUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null && !s.isBlank())
            return !userRepository.existsByUsername(s.toLowerCase());

        return false;
    }
}
