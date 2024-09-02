package com.grepp.boot.jwt;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException(String token) {
        super("Invalid JWT token: " + token);
    }

    public InvalidJwtTokenException(String token, Throwable cause) {
        super("Invalid JWT token: " + token, cause);
    }
}
