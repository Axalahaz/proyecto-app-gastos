package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.DomainException;

public class NoAutenticadoException extends DomainException {

    public NoAutenticadoException(String message) {
        super(message, ErrorType.NOT_AUTHENTICATED);
    }
}
