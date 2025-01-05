package com.az.edadi.auth.exception.handler;

import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.common_service.service.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InvalidPasswordHandler {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(InvalidPasswordException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", Translator.getTranslation(ex.getMessage()));
        errors.put("field", "password");
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
