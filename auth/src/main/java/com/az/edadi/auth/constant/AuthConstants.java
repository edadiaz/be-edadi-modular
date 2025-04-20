package com.az.edadi.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthConstants {

    REFRESH_TOKEN("refresh_token"),
    RESPONSE_MESSAGE("message"),
    USER_ID("user_id"),
    PARENT_TOKEN_ID("parent_token_id"),
    PERMISSIONS("permissions");

    private final String name;

}
