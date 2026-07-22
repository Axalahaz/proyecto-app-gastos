package com.finanzas.app.shared.exception.extend;

import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.DomainException;

public class ForbiddenException extends DomainException {

    public ForbiddenException(String message) {
        super(message, ErrorType.FORBIDDEN);
    }
}
