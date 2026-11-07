package com.finanzas.app.shared.exception.exceptionImpl;

import com.finanzas.app.shared.exception.DomainException;
import com.finanzas.app.shared.exception.model.ErrorType;

public class NoAutenticadoException extends DomainException {

    public NoAutenticadoException(String message) {
        super(message, ErrorType.NOT_AUTHENTICATED);
    }
}