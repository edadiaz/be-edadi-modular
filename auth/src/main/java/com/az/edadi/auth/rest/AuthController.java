package com.az.edadi.auth.rest;

import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.response.LoginResponseModel;
import com.az.edadi.auth.service.LoginService;
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
    ResponseEntity<LoginResponseModel> loginWithPassword(@RequestBody LoginWithPasswordRequest request) {
        return ResponseEntity.ok(loginService.loginWithPassword(request));

    }
}
