package com.finanzas.app.shared.exception.exceptionImpl;

import com.finanzas.app.shared.exception.DomainException;
import com.finanzas.app.shared.exception.model.ErrorType;

public class NotFoundException extends DomainException {
	
	// --------------------------------
	// CONSTRUCTOR

    private NotFoundException(String message) {
        super(message, ErrorType.NOT_FOUND);
    }
    
    // --------------------------------
    // factory methods

    public static NotFoundException of(String message) {
        return new NotFoundException(message);
    }
    
    public static NotFoundException of(String entity, Object id) {
        return new NotFoundException("El Recurso " + entity + " con id " + id + " no fue encontrado.");
    }
}