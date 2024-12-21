package com.az.edadi.user.service.impl;

import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.user.model.RegisterUserRequest;
import com.az.edadi.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(RegisterUserRequest request) {

    }
}
