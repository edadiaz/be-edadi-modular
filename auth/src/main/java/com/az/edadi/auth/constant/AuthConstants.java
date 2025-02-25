package com.az.edadi.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthConstants {

    REFRESH_TOKEN("refresh_token"),
    RESPONSE_MESSAGE("message");

    private final String name;

}
