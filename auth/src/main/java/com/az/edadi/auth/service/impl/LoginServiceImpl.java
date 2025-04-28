package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.constant.AuthConstants;
import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.exception.ExpiredTokenException;
import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.auth.model.TokenBody;
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
import com.az.edadi.dal.entity.auth.EdadiToken;
import com.az.edadi.dal.repository.auth.EdadiTokenRepository;
import com.az.edadi.model.exception.UserNotFoundException;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
    private final EdadiTokenRepository refreshTokenRepository;

    @Override
    public LoginWithPasswordResponse loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response) {
        request.setUsernameOrEmail(request.getUsernameOrEmail().toLowerCase());
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail()).orElseThrow(() -> new UserNotFoundException("user-not-found-with-username-or-email"));
        validatePassword(request.getPassword(), user.getPassword());
        return createLoginResponseModel(user, response,request.getFingerprint());
    }

    @Override
    public LoginWithGoogleResponse loginWithGoogle(LoginWithGoogleRequest request, HttpServletResponse response) {
        OAuth2CustomUser oAuth2CustomUser = oAuthService.getGoogleUser(request.token());
        var user = userRepository.findByEmail(oAuth2CustomUser.getEmail());
        return user.map(value -> new LoginWithGoogleResponse(createLoginResponseModel(value, response, null))).orElseGet(() -> new LoginWithGoogleResponse(oAuth2CustomUser));
    }

    @Override
    public LoginWithFacebookResponse loginWithFacebook(LoginWithFacebookRequest request, HttpServletResponse response) {
        OAuth2CustomUser oAuth2CustomUser = oAuthService.getFacebookUser(request.token());
        var user = userRepository.findByEmail(oAuth2CustomUser.getEmail());
        return user.map(value -> new LoginWithFacebookResponse(createLoginResponseModel(value, response,null))).orElseGet(() -> new LoginWithFacebookResponse(oAuth2CustomUser));
    }

    @Override
    public LoginWithPasswordResponse refreshToken(RefreshTokenRequest tokenRequest,HttpServletRequest servletRequest) {
        String refreshToken = CookieUtil.findCookie(servletRequest, AuthConstants.REFRESH_TOKEN.getName()).orElseThrow(ExpiredTokenException::new);
        var refreshTokenBody = jwtService.getTokenBody(TokenType.REFRESH_TOKEN, refreshToken);
        var tokenBody = new TokenBody(refreshTokenBody.getUserId(),
                refreshTokenBody.getTokenId(), List.of());
        return new LoginWithPasswordResponse(
                jwtService.generateToken(TokenType.ACCESS_TOKEN, tokenBody),
                jwtProperties.getRefreshTokenSessionTime());
    }


    void validatePassword(String input, String actualPassword) {
        if (!passwordEncoder.matches(input, actualPassword)) throw new InvalidPasswordException();
    }


    LoginWithPasswordResponse createLoginResponseModel(User user, HttpServletResponse response, String fingerPrint) {
        var refreshTokenBody = new TokenBody(user.getId(),fingerPrint);
        saveToken(refreshTokenBody);
        var accessTokenBody = new TokenBody(user.getId(), refreshTokenBody.getTokenId(), List.of());
        String accessToken = jwtService.generateToken(TokenType.ACCESS_TOKEN, accessTokenBody);
        String refreshToken = jwtService.generateToken(TokenType.REFRESH_TOKEN, refreshTokenBody);
        setTokenToResponse(response, refreshToken);
        return new LoginWithPasswordResponse(accessToken, jwtProperties.getRefreshTokenSessionTime());
    }



    void saveToken(TokenBody tokenBody) {
        var tokenEnt = new EdadiToken();
        tokenEnt.setTokenId(tokenBody.getTokenId());
        tokenEnt.setUserId(tokenBody.getUserId());
        tokenEnt.setIp("nn");
        tokenEnt.setStartDate(new Date());
        tokenEnt.setEndDate(jwtService.getExpirationDate(TokenType.REFRESH_TOKEN));
        tokenEnt.setActive(true);
        tokenEnt.setFingerPrint(tokenBody.getFingerPrint());
        refreshTokenRepository.save(tokenEnt);
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
