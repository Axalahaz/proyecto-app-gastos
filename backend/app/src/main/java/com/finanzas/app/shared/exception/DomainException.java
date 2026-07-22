package com.finanzas.app.shared.exception;

import java.util.List;

import com.finanzas.app.shared.error.ErrorType;

import lombok.Getter;

/*
 * Define contrato de excepciones
 * */
@Getter
public abstract class DomainException extends RuntimeException {
	
	private final ErrorType error;
	private final List<String> details;

    public DomainException(String message, ErrorType error) {
    	this(message, error, List.of());
    }
    
    public DomainException(String message, ErrorType error, List<String> details) {
        super(message);
        this.error = error;
        this.details = (details == null || details.isEmpty())
                ? List.of()
                : List.copyOf(details);
    }
}