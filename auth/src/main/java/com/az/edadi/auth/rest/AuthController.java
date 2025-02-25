package com.az.edadi.auth.rest;

import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.request.RefreshTokenRequest;
import com.az.edadi.auth.model.response.LoginResponse;
import com.az.edadi.auth.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final LoginService loginService;

    @PostMapping("login-with-password")
    ResponseEntity<LoginResponse> loginWithPassword(@RequestBody LoginWithPasswordRequest request,
                                                    HttpServletResponse response) {
        return ResponseEntity.ok(loginService.loginWithPassword(request, response));
    }

    @PostMapping("login-with-google")
    ResponseEntity<LoginResponse> loginWithGoogle(@RequestBody LoginWithGoogleRequest request,
                                                  HttpServletResponse response) {
        return ResponseEntity.ok(loginService.loginWithGoogle(request, response));
    }

    @PostMapping("refresh-token")
    ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest loginRequest,
                                               HttpServletRequest servletRequest) {
        return ResponseEntity.ok(loginService.refreshToken(loginRequest, servletRequest));
    }
}
