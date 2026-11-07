package com.finanzas.app.shared.exception.exceptionImpl;

import com.finanzas.app.shared.exception.DomainException;
import com.finanzas.app.shared.exception.model.ErrorType;

public class ConflictException extends DomainException {

	public ConflictException(String message) {
        super(message, ErrorType.CONFLICT);
    }
}