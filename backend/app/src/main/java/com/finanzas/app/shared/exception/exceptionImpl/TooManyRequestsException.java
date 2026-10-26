package com.finanzas.app.shared.exception.extend;

import java.util.List;

import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.DomainException;

public class TooManyRequestsException extends DomainException {

	// --------------------------------
	// CONSTRUCTOR
	
    private TooManyRequestsException(String message, List<String> details) {
        super(message, ErrorType.TOO_MANY_REQUESTS, details);
    }
    
    // --------------------------------
    // factory methods

    public TooManyRequestsException of(String message) {
    	return new TooManyRequestsException(message, List.of());
    }
    
    public TooManyRequestsException of(String message, List<String> details) {
    	return new TooManyRequestsException(message, details);
    }
}
