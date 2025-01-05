package com.az.edadi.auth.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("invalid-password");
    }
}
