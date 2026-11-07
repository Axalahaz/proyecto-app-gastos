package com.finanzas.app.shared.exception.exceptionImpl;

import com.finanzas.app.shared.exception.DomainException;
import com.finanzas.app.shared.exception.model.ErrorType;

public class ForbiddenException extends DomainException {

    public ForbiddenException(String message) {
        super(message, ErrorType.FORBIDDEN);
    }
}
