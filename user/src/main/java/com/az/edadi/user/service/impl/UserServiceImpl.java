package com.az.edadi.user.service.impl;

import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.user.adapter.UserAdapter;
import com.az.edadi.user.model.RegisterUserRequest;
import com.az.edadi.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    public UserServiceImpl(UserRepository userRepository,
                           UserAdapter userAdapter) {
        this.userRepository = userRepository;
        this.userAdapter = userAdapter;
    }

    @Override
    public void registerUser(RegisterUserRequest request) {
        User user = new User();
        userAdapter.map(user, request);
        userRepository.save(user);
    }
}
