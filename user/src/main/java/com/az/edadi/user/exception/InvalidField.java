package com.az.edadi.user.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvalidField {

    private String field;
    private String message;

    public InvalidField(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
