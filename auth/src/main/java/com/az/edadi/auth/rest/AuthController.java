package com.az.edadi.auth.rest;

import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.request.RefreshTokenRequest;
import com.az.edadi.auth.model.response.LoginWithPasswordResponse;
import com.az.edadi.auth.model.response.LoginWithGoogleResponse;
import com.az.edadi.auth.service.LoginService;
import com.az.edadi.auth.service.RegisterService;
import com.az.edadi.user.model.request.RegisterUserRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final LoginService loginService;
    private final RegisterService registerService;

    @PostMapping("sign-up")
    ResponseEntity<HttpStatus> registerUser(@RequestBody @Validated RegisterUserRequest request) {
        log.info("Registering user with email: {}", request.getEmail());
        registerService.registerUser(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @PostMapping("login-with-password")
    ResponseEntity<LoginWithPasswordResponse> loginWithPassword(@RequestBody LoginWithPasswordRequest request,
                                                                HttpServletResponse response) {
        return ResponseEntity.ok(loginService.loginWithPassword(request, response));
    }

    @PostMapping("login-with-google")
    ResponseEntity<LoginWithGoogleResponse> loginWithGoogle(@RequestBody LoginWithGoogleRequest request,
                                                            HttpServletResponse response) {
        return ResponseEntity.ok(loginService.loginWithGoogle(request, response));
    }

    @PostMapping("refresh-token")
    ResponseEntity<LoginWithPasswordResponse> refreshToken(@RequestBody RefreshTokenRequest loginRequest,
                                                           HttpServletRequest servletRequest) {
        return ResponseEntity.ok(loginService.refreshToken(loginRequest, servletRequest));
    }
}
