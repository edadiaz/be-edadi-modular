package com.az.edadi.auth.service;

import com.az.edadi.auth.model.response.OAuth2CustomUser;

public interface OAuthService {
    OAuth2CustomUser getGoogleUser(String token);
}
