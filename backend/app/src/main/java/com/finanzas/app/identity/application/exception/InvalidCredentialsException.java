package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.exception.DomainException;
import com.finanzas.app.shared.exception.model.ErrorType;

public class InvalidCredentialsException extends DomainException {

    public InvalidCredentialsException(String message) {
        super(message, ErrorType.INVALID_CREDENTIALS);
    }
}