package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.model.TokenBody;
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
    public String generateToken(TokenType type, String userId, List<String> permissions) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userId)
                .setIssuer("Edadi")
                .setIssuedAt(new Date())
                .claim(USER_ID, userId)
                .claim(PERMISSIONS, permissions)
                .signWith(getKey(type))
                .setExpiration(getExpirationDate(type))
                .compact();
    }

    @Override
    public TokenBody getTokenBody(TokenType tokenType, String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey(tokenType))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return new TokenBody(
                claims.getId(),
                claims.getSubject(),
                (List<String>) claims.get(PERMISSIONS)
        );
    }

    @Override
    public String getTokenId(TokenType tokenType, String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey(tokenType))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getId();
    }

    @Override
    public String getUserId(TokenType tokenType, String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getKey(tokenType))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get(USER_ID, String.class);
    }



    @Override
    public Date getExpirationDate(TokenType type) {
        //todo separate keys for access and refresh token
        return switch (type) {
            case ACCESS_TOKEN -> new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenSessionTime());
            case REFRESH_TOKEN -> new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenSessionTime());
            case RESET_PASSWORD_TOKEN -> new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenSessionTime());
        };
    }

    SecretKey getKey(TokenType type) {
        //todo separate keys for access and refresh token
        return switch (type) {
            case ACCESS_TOKEN -> Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
            case REFRESH_TOKEN -> Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
            case RESET_PASSWORD_TOKEN -> Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        };

    }


}
