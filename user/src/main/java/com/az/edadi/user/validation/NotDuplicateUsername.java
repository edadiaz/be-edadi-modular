package com.az.edadi.user.validation;

import com.az.edadi.user.validation.impl.DublicateUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Constraint(validatedBy = DublicateUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotDuplicateUsername {
    String message() default "duplicate-username";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}