package com.az.edadi.common_service.annotation;

import com.az.edadi.common_service.annotation.impl.DuplicateEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateEmailValidator.class)
public @interface NotDuplicateEmail {
    String message() default "duplicate-mail";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}