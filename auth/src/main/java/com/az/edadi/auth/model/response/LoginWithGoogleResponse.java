package com.az.edadi.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithGoogleResponse {

    private boolean isRegistered;
    private OAuth2CustomUser user;
    private LoginWithPasswordResponse loginResponse;

    public LoginWithGoogleResponse(OAuth2CustomUser user) {
        this.isRegistered = false;
        this.user = user;
    }

    public LoginWithGoogleResponse(LoginWithPasswordResponse loginWithPasswordResponse) {
        this.isRegistered = true;
        this.loginResponse=loginWithPasswordResponse;
     }
}
