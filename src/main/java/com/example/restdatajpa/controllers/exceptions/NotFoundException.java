package com.example.restdatajpa.controllers.exceptions;

public class NotFoundException extends RuntimeException {
    private static final String DESCRIPTION = "Not Found Request Exception (404)";

    public NotFoundException(String details) {
        super(DESCRIPTION + ". " + details);
    }
}
