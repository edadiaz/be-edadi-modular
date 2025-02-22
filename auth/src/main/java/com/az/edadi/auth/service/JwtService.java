package com.az.edadi.auth.service;

import java.util.List;
import java.util.UUID;

public interface JwtService {
    String generateAccessToken(UUID userId, String username, List<String> permissions);
    String generateRefreshToken(UUID userId);
    void verifyAccessToken(String token);
}
