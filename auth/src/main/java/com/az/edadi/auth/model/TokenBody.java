package com.az.edadi.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenBody {
    private String tokenId;
    private String userId;
    private String parentTokenId;
    private Date expirationDate;
    private List<String> permissions;
    private String fingerPrint;

    //for access token
    public TokenBody(String userId, String parentTokenId, List<String> permissions) {
        tokenId = UUID.randomUUID().toString();
        this.userId = userId;
        this.parentTokenId = parentTokenId;
        this.permissions = permissions;
    }

    //for refresh token
    public TokenBody(String userId) {
        tokenId = UUID.randomUUID().toString();
        this.userId = userId;
    }

    public TokenBody(String userId, String fingerPrint) {
        this(userId);
        this.fingerPrint = fingerPrint;
    }

}
