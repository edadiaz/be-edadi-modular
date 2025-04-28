package com.az.edadi.auth.service;

import com.az.edadi.auth.model.request.LoginWithFacebookRequest;
import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.request.RefreshTokenRequest;
import com.az.edadi.auth.model.response.LoginWithFacebookResponse;
import com.az.edadi.auth.model.response.LoginWithPasswordResponse;
import com.az.edadi.auth.model.response.LoginWithGoogleResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    LoginWithPasswordResponse loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response);
    LoginWithGoogleResponse loginWithGoogle(LoginWithGoogleRequest request, HttpServletResponse response);
    LoginWithFacebookResponse loginWithFacebook(LoginWithFacebookRequest request, HttpServletResponse response);
    LoginWithPasswordResponse refreshToken(RefreshTokenRequest tokenRequest,HttpServletRequest servletRequest);
}