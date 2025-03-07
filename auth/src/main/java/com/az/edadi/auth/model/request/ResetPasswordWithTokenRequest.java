package com.az.edadi.auth.model.request;

public record ResetPasswordWithTokenRequest(String newPassword, String token) {
}
