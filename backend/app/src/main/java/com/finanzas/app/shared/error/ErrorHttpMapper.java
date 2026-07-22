package com.finanzas.app.shared.error;

import org.springframework.http.HttpStatus;

public class ErrorHttpMapper {

    public static HttpStatus toHttpStatus(ErrorType type) {
        return switch (type) {
            case INVALID_REQUEST, INVALID_BODY -> HttpStatus.BAD_REQUEST;

            case NOT_AUTHENTICATED, INVALID_CREDENTIALS -> HttpStatus.UNAUTHORIZED;

            case FORBIDDEN -> HttpStatus.FORBIDDEN;

            case NOT_FOUND -> HttpStatus.NOT_FOUND;

            case METHOD_NOT_ALLOWED -> HttpStatus.METHOD_NOT_ALLOWED;

            case CONFLICT -> HttpStatus.CONFLICT;

            case VALIDATION_ERROR -> HttpStatus.UNPROCESSABLE_CONTENT;

            case TOO_MANY_REQUESTS -> HttpStatus.TOO_MANY_REQUESTS;

            case INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;

            case SERVICE_UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE;
        };
    }
}