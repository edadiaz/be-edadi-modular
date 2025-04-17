package com.az.edadi.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;
@Data
@AllArgsConstructor
public class StompPrincipal implements Principal {

    private String userId;
    private String refreshTokenId;
    @Override
    public String getName() {
        return userId;
    }
}
