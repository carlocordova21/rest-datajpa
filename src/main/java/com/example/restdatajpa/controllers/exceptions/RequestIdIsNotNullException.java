package com.example.restdatajpa.controllers.exceptions;

public class RequestIdIsNotNullException extends BadRequestException {
    private static final String DESCRIPTION = "Request id is null";

    public RequestIdIsNotNullException(String details) {
        super(DESCRIPTION + ". " + details);
    }
}
