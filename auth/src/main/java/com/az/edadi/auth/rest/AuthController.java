package com.az.edadi.auth.rest;

import com.az.edadi.auth.model.request.*;
import com.az.edadi.auth.model.request.LoginWithFacebookRequest;
import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.request.RefreshTokenRequest;
import com.az.edadi.auth.model.response.LoginWithFacebookResponse;
import com.az.edadi.auth.model.response.LoginWithPasswordResponse;
import com.az.edadi.auth.model.response.LoginWithGoogleResponse;
import com.az.edadi.auth.service.LoginService;
import com.az.edadi.auth.service.PasswordService;
import com.az.edadi.auth.service.PermissionService;
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
    private final PasswordService passwordService;
    private final PermissionService permissionService;
    @PostMapping("sign-up")
    ResponseEntity<LoginWithPasswordResponse> registerUser(@RequestBody @Validated RegisterUserRequest request,HttpServletResponse response) {
        log.info("Registering user with email: {}", request.getEmail());
        return ResponseEntity.ok(registerService.registerUser(request,response));
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

    @PostMapping("login-with-facebook")
    ResponseEntity<LoginWithFacebookResponse> loginWithGoogle(@RequestBody LoginWithFacebookRequest request,
                                                              HttpServletResponse response) {
        return ResponseEntity.ok(loginService.loginWithFacebook(request, response));
    }

    @PostMapping("refresh-token")
    ResponseEntity<LoginWithPasswordResponse> refreshToken(@RequestBody RefreshTokenRequest loginRequest,
                                                           HttpServletRequest servletRequest) {
        return ResponseEntity.ok(loginService.refreshToken(loginRequest, servletRequest));
    }

    @PostMapping("forgot-password")
    ResponseEntity<HttpStatus> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        passwordService.sendResetPasswordEmail(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("reset-password-with-token")
    ResponseEntity<HttpStatus> resetPassword(@RequestBody ResetPasswordWithTokenRequest request) {
        passwordService.resetPasswordWithToken(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("give-permission")
    ResponseEntity<HttpStatus> resetPassword(@RequestBody GivePermissionRequest request) {
        permissionService.givePermissionToUser(request);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
