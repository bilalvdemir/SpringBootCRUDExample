package com.bilald.crudsample.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // User errors (4xxx)
    USER_NOT_FOUND("4001", "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("4002", "User already exists", HttpStatus.CONFLICT),
    USERNAME_ALREADY_TAKEN("4003", "Username is already taken", HttpStatus.CONFLICT),
    EMAIL_ALREADY_REGISTERED("4004", "Email is already registered", HttpStatus.CONFLICT),

    // Validation errors (4xxx)
    VALIDATION_ERROR("4100", "Validation failed", HttpStatus.BAD_REQUEST),
    INVALID_INPUT("4101", "Invalid input provided", HttpStatus.BAD_REQUEST),

    // System errors (5xxx)
    INTERNAL_SERVER_ERROR("5000", "Internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR("5001", "Database operation failed", HttpStatus.INTERNAL_SERVER_ERROR),

    // Rate limiting (4xxx)
    RATE_LIMIT_EXCEEDED("4290", "Too many requests", HttpStatus.TOO_MANY_REQUESTS);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
