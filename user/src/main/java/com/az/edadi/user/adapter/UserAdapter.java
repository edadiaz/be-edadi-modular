package com.az.edadi.user.adapter;

import com.az.edadi.dal.entity.User;
import com.az.edadi.user.model.RegisterUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {
    public void map(User user, RegisterUserRequest request) {
        user.setName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
    }
}
