package com.az.edadi.auth.service;

import com.az.edadi.auth.model.request.ForgotPasswordRequest;
import com.az.edadi.auth.model.request.ResetPasswordWithTokenRequest;

public interface PasswordService {
    void sendResetPasswordEmail(ForgotPasswordRequest forgotPasswordRequest);
    void resetPasswordWithToken(ResetPasswordWithTokenRequest request);
}
