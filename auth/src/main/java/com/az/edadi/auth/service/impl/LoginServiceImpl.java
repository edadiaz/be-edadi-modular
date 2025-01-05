package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.adapter.AuthAdapter;
import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.response.LoginResponseModel;
import com.az.edadi.auth.property.JwtProperties;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.auth.service.LoginService;
import com.az.edadi.common_model.exception.UserNotFoundException;
import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final AuthAdapter authAdapter;

    @Override
    public LoginResponseModel loginWithPassword(LoginWithPasswordRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("user-not-found-with-username-or-email"));
        validatePassword(request.getPassword(), user.getPassword());
        return createLoginResponseModel(user);
    }

    void validatePassword(String input, String actualPassword) {
        if (!passwordEncoder.matches(input, actualPassword))
            throw new InvalidPasswordException();
    }

    LoginResponseModel createLoginResponseModel(User user) {
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), List.of());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername(), user.getId());
        return new LoginResponseModel(accessToken, refreshToken, jwtProperties.getRefreshTokenSessionTime(), authAdapter.map(user));
    }
}
