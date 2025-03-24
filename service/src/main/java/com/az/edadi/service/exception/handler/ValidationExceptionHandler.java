package com.az.edadi.service.exception.handler;

import com.az.edadi.service.service.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        var errors = bindingResult.getFieldErrors().stream().map(error ->
                Map.of("field", error.getField(), "message", Translator.getTranslation(error.getDefaultMessage()))
        ).toList();
        response.put("errors",errors);
//        bindingResult.getFieldErrors().forEach(error ->
//                errors.put(error.getField(), Translator.getTranslation(error.getDefaultMessage()))
//        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}