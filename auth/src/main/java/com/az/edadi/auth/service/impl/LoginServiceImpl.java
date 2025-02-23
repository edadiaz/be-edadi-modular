package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.exception.InvalidPasswordException;
import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.response.LoginResponseModel;
import com.az.edadi.auth.model.response.OAuth2CustomUser;
import com.az.edadi.auth.property.GoogleClientProperties;
import com.az.edadi.auth.property.JwtProperties;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.auth.service.LoginService;
import com.az.edadi.common_model.exception.UserNotFoundException;
import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final GoogleClientProperties googleClientProperties;

    @Override
    public LoginResponseModel loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("user-not-found-with-username-or-email"));
        validatePassword(request.getPassword(), user.getPassword());
        return createLoginResponseModel(user, response);
    }

    @Override
    public LoginResponseModel loginWithGoogle(LoginWithGoogleRequest request, HttpServletResponse response) {
            OAuth2CustomUser oAuth2CustomUser = verifyToken(request.token());
//        User user = userRepository.findByEmail(oAuth2CustomUser.getEmail()).orElseThrow();
        return null;
    }

    OAuth2CustomUser verifyToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<OAuth2CustomUser> result = restTemplate.exchange(uri, HttpMethod.GET, entity, OAuth2CustomUser.class);
        return null;
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
        return new LoginResponseModel(accessToken, jwtProperties.getRefreshTokenSessionTime());
    }
}
