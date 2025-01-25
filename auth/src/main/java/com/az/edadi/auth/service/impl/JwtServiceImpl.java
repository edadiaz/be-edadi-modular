package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.property.JwtProperties;
import com.az.edadi.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    private final String USER_ID = "userId";
    private final String PERMISSIONS = "permissions";

    @Override
    public String generateAccessToken(UUID userId, String username, List<String> permissions) {
        return Jwts.builder()
                .signWith(getKey())
                .setSubject(username)
                .setIssuer("Edadi")
                .setIssuedAt(new Date())
                .claim(USER_ID, userId)
                .claim(PERMISSIONS, permissions)
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
                .claim(USER_ID, userId)
                .setExpiration(getExpirationDate(TokenType.REFRESH))
                .compact();
    }

    @Override
    public void verifyAccessToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            setToContext(claims);
        } catch (Exception e) {
            throw new RuntimeException("Invalid access token");
        }
    }

    void setToContext(Claims claims) {

        List<String> permissions = (List<String>) claims.get(PERMISSIONS);
        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        String userId = claims.get(USER_ID, String.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                userId,
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
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
