package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.constant.AuthConstants;
import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.exception.ExpiredTokenException;
import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.auth.model.request.LoginWithFacebookRequest;
import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.request.RefreshTokenRequest;
import com.az.edadi.auth.model.response.LoginWithFacebookResponse;
import com.az.edadi.auth.model.response.LoginWithPasswordResponse;
import com.az.edadi.auth.model.response.LoginWithGoogleResponse;
import com.az.edadi.auth.model.response.OAuth2CustomUser;
import com.az.edadi.auth.property.JwtProperties;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.auth.service.LoginService;
import com.az.edadi.auth.service.OAuthService;
import com.az.edadi.auth.util.CookieUtil;
import com.az.edadi.model.exception.UserNotFoundException;
import com.az.edadi.dal.entity.auth.RefreshToken;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.dal.repository.auth.RefreshTokenRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final OAuthService oAuthService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public LoginWithPasswordResponse loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response) {
        request.setUsernameOrEmail(request.getUsernameOrEmail().toLowerCase());
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail()).orElseThrow(() -> new UserNotFoundException("user-not-found-with-username-or-email"));
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
    public LoginWithFacebookResponse loginWithFacebook(LoginWithFacebookRequest request, HttpServletResponse response) {
        OAuth2CustomUser oAuth2CustomUser = oAuthService.getFacebookUser(request.token());
        var user = userRepository.findByEmail(oAuth2CustomUser.getEmail());
        return user.map(value -> new LoginWithFacebookResponse(createLoginResponseModel(value, response))).orElseGet(() -> new LoginWithFacebookResponse(oAuth2CustomUser));
    }

    @Override
    public LoginWithPasswordResponse refreshToken(RefreshTokenRequest tokenRequest, HttpServletRequest servletRequest) {
        String refreshToken = CookieUtil.findCookie(servletRequest, AuthConstants.REFRESH_TOKEN.getName()).orElseThrow(ExpiredTokenException::new);
        var tokenId = jwtService.getTokenId(TokenType.REFRESH_TOKEN, refreshToken);
        var savedToken = refreshTokenRepository.findByTokenId(tokenId);
        if (!tokenRequest.getUserId().equals(savedToken.getUserId())) throw new RuntimeException();
        return userRepository.findById(tokenRequest.getUserId()).map(this::createLoginResponseForRefreshToken).orElseThrow();
    }


    void validatePassword(String input, String actualPassword) {
        if (!passwordEncoder.matches(input, actualPassword)) throw new InvalidPasswordException();
    }


    LoginWithPasswordResponse createLoginResponseModel(User user, HttpServletResponse response) {
        String accessToken = jwtService.generateToken(TokenType.ACCESS_TOKEN, user.getId(), List.of());
        String refreshToken = jwtService.generateToken(TokenType.REFRESH_TOKEN, user.getId(), List.of());
        saveToken(refreshToken);
        setTokenToResponse(response, refreshToken);
        return new LoginWithPasswordResponse(accessToken, jwtProperties.getRefreshTokenSessionTime());
    }

    LoginWithPasswordResponse createLoginResponseForRefreshToken(User user) {
        String accessToken = jwtService.generateToken(TokenType.ACCESS_TOKEN, user.getId(), List.of());
        return new LoginWithPasswordResponse(accessToken, jwtService.getExpirationDate(TokenType.ACCESS_TOKEN).getTime());
    }

    void saveToken(String token) {
        var tokenReq = new RefreshToken();
        tokenReq.setTokenId(jwtService.getTokenId(TokenType.REFRESH_TOKEN, token));
        tokenReq.setUserId(jwtService.getUserId(TokenType.REFRESH_TOKEN, token));
        tokenReq.setIp("nn");
        tokenReq.setStartDate(new Date());
        tokenReq.setEndDate(jwtService.getExpirationDate(TokenType.REFRESH_TOKEN));
        refreshTokenRepository.save(tokenReq);
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
