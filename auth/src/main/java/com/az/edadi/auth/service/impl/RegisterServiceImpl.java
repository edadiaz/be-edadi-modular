package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.adapter.AuthAdapter;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.response.LoginWithPasswordResponse;
import com.az.edadi.auth.service.LoginService;
import com.az.edadi.auth.service.RegisterService;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.user.model.request.RegisterUserRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final AuthAdapter authAdapter;
    private final UserRepository userRepository;
    private final LoginService loginService;

    @Override
    public LoginWithPasswordResponse registerUser(RegisterUserRequest request, HttpServletResponse response) {
        User user = new User();
        authAdapter.map(user, request);
        userRepository.save(user);
        return loginService.loginWithPassword(
                new LoginWithPasswordRequest(request.getUsername(),
                        request.getPassword(), null),
                response);
    }

}

