package com.az.edadi.auth.service;

import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.request.RefreshTokenRequest;
import com.az.edadi.auth.model.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    LoginResponse loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response);

    LoginResponse loginWithGoogle(LoginWithGoogleRequest request, HttpServletResponse response);

    LoginResponse refreshToken(RefreshTokenRequest tokenRequest, HttpServletRequest servletRequest);
}