package com.az.edadi.auth.service;

import com.az.edadi.auth.model.request.LoginWithPasswordRequest;
import com.az.edadi.auth.model.response.LoginResponseModel;

public interface LoginService {
    LoginResponseModel loginWithPassword(LoginWithPasswordRequest request);
}
