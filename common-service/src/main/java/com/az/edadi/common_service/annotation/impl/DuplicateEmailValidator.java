package com.az.edadi.common_service.annotation.impl;

import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.common_service.annotation.NotDuplicateEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class DuplicateEmailValidator implements ConstraintValidator<NotDuplicateEmail, String> {

    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.hasText(s))
            return !userRepository.existsByEmail(s.toLowerCase());
        return false;
    }
}
