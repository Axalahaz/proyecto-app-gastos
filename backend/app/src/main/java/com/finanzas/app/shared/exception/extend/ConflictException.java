package com.finanzas.app.shared.exception.extend;

import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.DomainException;

public class ConflictException extends DomainException {

	public ConflictException(String message) {
        super(message, ErrorType.CONFLICT);
    }
}