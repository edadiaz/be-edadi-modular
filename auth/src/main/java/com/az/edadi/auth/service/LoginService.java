package com.az.edadi.auth.service;

import com.az.edadi.auth.model.request.LoginWithGoogleRequest;
import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.response.LoginResponseModel;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    LoginResponseModel loginWithPassword(LoginWithPasswordRequest request, HttpServletResponse response);
    LoginResponseModel loginWithGoogle(LoginWithGoogleRequest request, HttpServletResponse response);
}
