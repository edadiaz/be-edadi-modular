package com.az.edadi.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithPasswordRequest {
    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;
    private String fingerprint;
}
