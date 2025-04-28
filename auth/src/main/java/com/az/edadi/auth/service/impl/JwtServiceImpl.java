package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.model.TokenBody;
import com.az.edadi.auth.property.JwtProperties;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.dal.repository.auth.EdadiTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static com.az.edadi.auth.constant.AuthConstants.PARENT_TOKEN_ID;
import static com.az.edadi.auth.constant.AuthConstants.PERMISSIONS;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${spring.application.name}")
    private String appName;

    private final JwtProperties jwtProperties;
    private final EdadiTokenRepository tokenRepository;

    @Override
    public String generateToken(TokenType tokenType, TokenBody tokenBody) {
        var builder = Jwts.builder().setId(tokenBody.getTokenId())
                .setSubject(tokenBody.getUserId())
                .setIssuer(appName)
                .setIssuedAt(new Date())
                .signWith(getKey(tokenType))
                .setExpiration(getExpirationDate(tokenType));

        if (StringUtils.hasText(tokenBody.getParentTokenId()))
            builder.claim(PARENT_TOKEN_ID.getName(), tokenBody.getParentTokenId());

        if (tokenBody.getPermissions() != null && !tokenBody.getPermissions().isEmpty()) {
            builder.claim(PERMISSIONS.getName(), tokenBody.getPermissions());
        }

        return builder.compact();

    }

    @Override
    public TokenBody getTokenBody(TokenType tokenType, String token) {
        Claims claims = getClaims(tokenType, token);
        var body = new TokenBody(
                claims.getId(),
                claims.getSubject(),
                claims.get(PARENT_TOKEN_ID.getName(), String.class),
                claims.getExpiration(),
                getPermissionsFromToken(claims),
                null
        );
        validateToken(tokenType, body);
        return body;

    }


    private Claims getClaims(TokenType type, String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey(type))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpirationDate(TokenType type) {
        //todo separate keys for access and refresh token
        return switch (type) {
            case ACCESS_TOKEN -> new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenSessionTime());
            case REFRESH_TOKEN -> new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenSessionTime());
            case RESET_PASSWORD_TOKEN ->
                    new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenSessionTime());
        };
    }

    @Override
    @CacheEvict(value = "edadi_token", key = "#tokenId")
    public void deactivateToken(String tokenId) {
        var edadiToken = tokenRepository.findByTokenId(tokenId);
        edadiToken.setActive(false);
        tokenRepository.save(edadiToken);
    }

    SecretKey getKey(TokenType type) {
        //todo separate keys for access and refresh token
        return switch (type) {
            case ACCESS_TOKEN -> Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
            case REFRESH_TOKEN -> Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
            case RESET_PASSWORD_TOKEN ->
                    Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        };

    }

    private List<String> getPermissionsFromToken(Claims claims) {
        var permissions = claims.get(PERMISSIONS.getName());
        if (permissions instanceof List<?> list)
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        return Collections.emptyList();
    }

    void validateToken(TokenType type, TokenBody tokenBody) {
        if (tokenBody.getExpirationDate().before(new Date())) {
            throw new RuntimeException("Token expired");
        }
        String tokenId = switch (type) {
            case REFRESH_TOKEN, RESET_PASSWORD_TOKEN -> tokenBody.getTokenId();
            case ACCESS_TOKEN -> tokenBody.getParentTokenId();
        };
        var token = tokenRepository.findByTokenId(tokenId);

        if (!token.isActive())
            throw new RuntimeException("Token is removed");

    }


}
