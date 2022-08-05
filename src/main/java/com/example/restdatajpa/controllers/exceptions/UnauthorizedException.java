package com.example.restdatajpa.controllers.exceptions;

public class UnauthorizedException extends RuntimeException {
    private static final String DESCRIPTION = "Unauthorized Exception (401)";

    public UnauthorizedException(String details) {
        super(DESCRIPTION + ". " + details);
    }
}
