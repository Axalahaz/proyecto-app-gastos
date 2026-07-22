package com.finanzas.app.shared.exception.extend;

import java.util.List;

import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.DomainException;

public class ValidationException extends DomainException {

	// --------------------------------
	// CONSTRUCTOR
	
    private ValidationException(String message, List<String> details) {
        super(message, ErrorType.VALIDATION_ERROR, details);
    }
    
    
    // --------------------------------
    // factory methods
    
    public static ValidationException of(String message) {
        return new ValidationException(message, List.of());
    }

    public static ValidationException of(String message, List<String> details) {
    	return new ValidationException(message, details);
    }
}
