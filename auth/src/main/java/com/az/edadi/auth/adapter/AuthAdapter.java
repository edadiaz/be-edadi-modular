package com.az.edadi.auth.adapter;

import com.az.edadi.dal.entity.User;
import com.az.edadi.user.model.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthAdapter {
    private final PasswordEncoder passwordEncoder;

    public void map(User user, RegisterUserRequest request) {
        user.setName(request.getFullName().toLowerCase());
        user.setEmail(request.getEmail().toLowerCase());
        user.setUsername(request.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    }

}
