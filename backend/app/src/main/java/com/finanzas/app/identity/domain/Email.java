package com.finanzas.app.identity.domain;

import java.util.regex.Pattern;

import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

public record Email(String value) {
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
		);

    public Email {
    	if (value == null || value.isBlank()) {
            throw ValidationException.of("El email es inválido");
        }
        
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw ValidationException.of("El formato del email es inválido");
        }
    	
    	value = value.trim();
    }
}