package com.az.edadi.auth.exception;

public class UserBlacklistedException extends RuntimeException{
    public UserBlacklistedException(String message) {
        super(message);
    }
}
