package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.property.JwtProperties;
import com.az.edadi.auth.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(UUID userId, String username, List<String> permissions) {
        return Jwts.builder()
                .signWith(getKey())
                .setSubject(username)
                .setIssuer("Edadi")
                .setIssuedAt(new Date())
                .claim("userId", userId)
                .claim("permissions", permissions)
                .setExpiration(getExpirationDate(TokenType.ACCESS))
                .compact();
    }

    @Override
    public String generateRefreshToken(String username, UUID userId) {
        return Jwts.builder()
                .signWith(getKey())
                .setSubject(username)
                .setIssuer("Edadi")
                .setIssuedAt(new Date())
                .claim("userId", userId)
                .setExpiration(getExpirationDate(TokenType.REFRESH))
                .compact();
    }

    Date getExpirationDate(TokenType type) {
        return switch (type) {
            case ACCESS -> new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenSessionTime());
            case REFRESH -> new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenSessionTime());
        };
    }

    SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    enum TokenType {
        ACCESS, REFRESH
    }
}
