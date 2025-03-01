package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.constant.AuthConstants;
import com.az.edadi.auth.exception.ExpiredTokenException;
import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.request.RefreshTokenRequest;
import com.az.edadi.auth.model.response.LoginWithPasswordResponse;
import com.az.edadi.auth.model.response.LoginWithGoogleResponse;
import com.az.edadi.auth.model.response.OAuth2CustomUser;
import com.az.edadi.auth.property.GoogleClientProperties;
import com.az.edadi.auth.property.JwtProperties;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.auth.service.LoginService;
import com.az.edadi.auth.service.OAuthService;
import com.az.edadi.auth.util.AuthUtil;
import com.az.edadi.common_model.exception.UserNotFoundException;
import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.no_sql.repository.RefreshTokenRepository;
import com.az.edadi.dal.no_sql.table.RefreshToken;
import com.az.edadi.dal.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final GoogleClientProperties googleClientProperties;
    private final RefreshTokenRepository tokenRepository;
    private final OAuthService oAuthService;

    @Override
    public LoginWithPasswordResponse loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("user-not-found-with-username-or-email"));
        validatePassword(request.getPassword(), user.getPassword());
        return createLoginResponseModel(user, response);
    }

    @Override
    public LoginWithGoogleResponse loginWithGoogle(LoginWithGoogleRequest request, HttpServletResponse response) {
        OAuth2CustomUser oAuth2CustomUser = oAuthService.getGoogleUser(request.token());
        var user = userRepository.findByEmail(oAuth2CustomUser.getEmail());
        return user.map(value -> new LoginWithGoogleResponse(createLoginResponseModel(value, response))).orElseGet(() -> new LoginWithGoogleResponse(oAuth2CustomUser));
    }

    @Override
    public LoginWithPasswordResponse refreshToken(RefreshTokenRequest tokenRequest, HttpServletRequest servletRequest) {
        //todo change it cookie not found exception
        String refreshToken = AuthUtil.findCookie(servletRequest, AuthConstants.REFRESH_TOKEN.getName())
                .orElseThrow(ExpiredTokenException::new);
        var savedToken = tokenRepository.findByTokenId(jwtService.getRefreshTokenId(refreshToken));
        //todo handle exception
        if (!tokenRequest.getUserId().equals(savedToken.getUserId()))
            throw new RuntimeException();
        return userRepository.findById(UUID.fromString(tokenRequest.getUserId()))
                .map(this::createLoginResponseForRefreshToken)
                .orElseThrow();


    }


    void validatePassword(String input, String actualPassword) {
        if (!passwordEncoder.matches(input, actualPassword))
            throw new InvalidPasswordException();
    }


    LoginWithPasswordResponse createLoginResponseModel(User user, HttpServletResponse response) {
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), List.of());
        String refreshToken = jwtService.generateRefreshToken();
        saveToken(user.getId().toString(), refreshToken);
        setTokenToResponse(response, refreshToken);
        return new LoginWithPasswordResponse(accessToken, jwtProperties.getRefreshTokenSessionTime());
    }

    LoginWithPasswordResponse createLoginResponseForRefreshToken(User user) {
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), List.of());
        return new LoginWithPasswordResponse(accessToken, jwtProperties.getRefreshTokenSessionTime());
    }

    void saveToken(String token, String userId) {
        var tokenRequest = new RefreshToken(jwtService.getRefreshTokenId(token), userId, LocalDate.now());
        tokenRepository.saveToken(tokenRequest);
    }

    void setTokenToResponse(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(AuthConstants.REFRESH_TOKEN.getName(), token);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge((int) Duration.ofDays(30).getSeconds());
//        cookie.setDomain("edadi.az");
        response.addCookie(cookie);
    }


}
