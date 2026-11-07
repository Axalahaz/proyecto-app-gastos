package com.finanzas.app.shared.domain.vo;

import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

public record CategoriaId(Long value) {
	
    public CategoriaId {
        if (value == null) {
            throw ValidationException.of("La categoria es obligatoria");
        }

        if (value <= 0) {
        	throw ValidationException.of("La categoria debe ser valida");
        }
    }
}