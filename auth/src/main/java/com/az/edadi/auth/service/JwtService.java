package com.az.edadi.auth.service;

import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.model.TokenBody;

import java.util.Date;

public interface JwtService {
    String generateToken(TokenType tokenType, TokenBody tokenBody);

    TokenBody getTokenBody(TokenType tokenType, String token);

    Date getExpirationDate(TokenType type);

    void deactivateToken(String tokenId);

}
