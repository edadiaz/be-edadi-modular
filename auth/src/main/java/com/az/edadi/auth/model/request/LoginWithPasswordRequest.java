package com.az.edadi.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithPasswordRequest {
    private String usernameOrEmail;
    private String password;
}
