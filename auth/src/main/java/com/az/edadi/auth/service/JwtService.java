package com.az.edadi.auth.service;

import java.util.List;
import java.util.UUID;

public interface JwtService {
    String generateAccessToken(String userId, String username, List<String> permissions);
    String generateRefreshToken();
    String getRefreshTokenId(String token);
    String getUserIdFromToken(String token);
    void verifyAccessToken(String token);

}
