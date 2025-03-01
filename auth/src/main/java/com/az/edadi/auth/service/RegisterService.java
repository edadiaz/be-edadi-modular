package com.az.edadi.auth.service;

import com.az.edadi.user.model.request.RegisterUserRequest;

public interface RegisterService {
    void registerUser(RegisterUserRequest request);

}
