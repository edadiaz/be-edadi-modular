package com.az.edadi.auth.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("expired-token");
    }
}
