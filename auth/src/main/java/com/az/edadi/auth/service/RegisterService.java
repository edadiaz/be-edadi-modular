package com.az.edadi.auth.service;

import com.az.edadi.auth.model.response.LoginWithPasswordResponse;
import com.az.edadi.auth.model.request.RegisterUserRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface RegisterService {
    LoginWithPasswordResponse registerUser(RegisterUserRequest request, HttpServletResponse response);

}
