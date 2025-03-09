package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.adapter.AuthAdapter;
import com.az.edadi.auth.service.RegisterService;
import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.user.model.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final AuthAdapter authAdapter;
    private final UserRepository userRepository;

    @Override
    public void registerUser(RegisterUserRequest request) {
        User user = new User();
        authAdapter.map(user, request);
        userRepository.save(user);
    }

}

