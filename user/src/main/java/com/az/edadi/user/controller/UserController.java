package com.az.edadi.user.controller;

import com.az.edadi.user.model.RegisterUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @PostMapping
    ResponseEntity<HttpStatus> registerUser(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
