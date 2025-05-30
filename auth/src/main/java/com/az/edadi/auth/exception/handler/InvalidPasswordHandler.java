package com.az.edadi.auth.exception.handler;

import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.service.service.Translator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InvalidPasswordHandler {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(InvalidPasswordException ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("path",  request.getRequestURI());
        response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        var errors = Map.of("field", "password", "message", Translator.getTranslation(ex.getMessage()));
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
