package com.example.restdatajpa.controllers.exceptions;

public class ForbiddenException extends RuntimeException {
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenException(String details) {
        super(DESCRIPTION + ". " + details);
    }
}
