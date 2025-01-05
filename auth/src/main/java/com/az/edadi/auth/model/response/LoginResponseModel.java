package com.az.edadi.auth.model.response;

import com.az.edadi.common_model.response.UserRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseModel {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private UserRes user;
}
