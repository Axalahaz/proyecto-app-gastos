package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.DomainException;

public class InvalidCredentialsException extends DomainException {

    public InvalidCredentialsException(String message) {
        super(message, ErrorType.INVALID_CREDENTIALS);
    }
}
