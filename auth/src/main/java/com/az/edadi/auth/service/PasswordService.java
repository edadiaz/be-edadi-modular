package com.az.edadi.auth.service;

import com.az.edadi.auth.model.request.ForgotPasswordRequest;

public interface PasswordService {
    void sendResetPasswordEmail(ForgotPasswordRequest forgotPasswordRequest);
}
