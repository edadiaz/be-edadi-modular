package com.az.edadi.auth.exception.handler;

import com.az.edadi.auth.constant.AuthConstants;
import com.az.edadi.auth.exception.ExpiredTokenException;
import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.service.service.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExpiredTokenExceptionHandler extends RuntimeException {
    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<Map<String, String>> handleExpiredTokenException(ExpiredTokenException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(AuthConstants.RESPONSE_MESSAGE.getName(), Translator.getTranslation(ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }
}
