package com.az.edadi.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithPasswordResponse {
    private String accessToken;
    private Long expiresIn;
}
