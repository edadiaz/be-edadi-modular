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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
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
    public LoginResponseModel loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("user-not-found-with-username-or-email"));
        validatePassword(request.getPassword(), user.getPassword());
        return createLoginResponseModel(user,response);
    }

    void validatePassword(String input, String actualPassword) {
        if (!passwordEncoder.matches(input, actualPassword))
            throw new InvalidPasswordException();
    }

    LoginResponseModel createLoginResponseModel(User user, HttpServletResponse response) {
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), List.of());
        String refreshToken = jwtService.generateRefreshToken(user.getId());
        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge((int) Duration.ofDays(30).getSeconds());
//        cookie.setDomain("edadi.az");
        response.addCookie(cookie);
        return new LoginResponseModel(accessToken,jwtProperties.getRefreshTokenSessionTime());
    }
}
