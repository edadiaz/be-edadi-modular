package com.az.edadi.user.controller;

import com.az.edadi.user.model.RegisterUserRequest;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("sign-up")
    ResponseEntity<HttpStatus> registerUser(@RequestBody RegisterUserRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
