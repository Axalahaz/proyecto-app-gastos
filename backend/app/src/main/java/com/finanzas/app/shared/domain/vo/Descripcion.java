package com.finanzas.app.shared.domain.vo;

import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

public record Descripcion(String value) {

    public Descripcion {
    	if (value == null || value.isBlank()) {
            throw ValidationException.of("La descripcion es obligatoria");
        }
    	
    	if (value.length() > 50) {
            throw ValidationException.of("La descripcion es demasiado larga Max. 50 Caracteres");
        }
    	
    	value = value.trim();
    }
}