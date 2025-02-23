package com.az.edadi.auth.model.response;

import lombok.Data;

@Data
public class OAuth2CustomUser {
    private String email;
    private String name;
    private String picture;
    private String provider;
}
