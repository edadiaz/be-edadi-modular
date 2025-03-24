package com.az.edadi.service.exception;

import com.az.edadi.model.exception.UserNotFoundException;
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
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(UserNotFoundException ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        response.put("path",  request.getRequestURI());
        var errors = Map.of("field","usernameOrEmail","message", Translator.getTranslation(ex.getMessage()));
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}