package com.finanzas.app.shared.exception.extend;

import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.DomainException;

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
        return new NotFoundException(entity + " no encontrado con id: " + id);
    }
}