package com.az.edadi.user.adapter;

import com.az.edadi.dal.entity.User;
import com.az.edadi.user.model.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    private final PasswordEncoder passwordEncoder;
    public void map(User user, RegisterUserRequest request) {
        user.setName(request.getFullName().toLowerCase());
        user.setEmail(request.getEmail().toLowerCase());
        user.setUsername(request.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    }
}
