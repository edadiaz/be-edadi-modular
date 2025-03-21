package com.az.edadi.auth.service;

import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.model.TokenBody;

import java.util.Date;
import java.util.List;

public interface JwtService {
    String generateToken(TokenType type, String userId, List<String> permissions);

    TokenBody getTokenBody(TokenType tokenType, String token);

    String getTokenId(TokenType tokenType, String token);

    String getUserId(TokenType tokenType, String token);

    Date getExpirationDate(TokenType type);


}
