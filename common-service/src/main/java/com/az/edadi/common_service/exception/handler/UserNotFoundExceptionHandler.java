package com.az.edadi.common_service.exception.handler;

import com.az.edadi.common_model.exception.UserNotFoundException;
import com.az.edadi.common_service.service.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(UserNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", Translator.getTranslation(ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}