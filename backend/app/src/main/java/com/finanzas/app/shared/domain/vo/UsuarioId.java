package com.finanzas.app.shared.domain.vo;

import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

public record UsuarioId(Long value) {
	
    public UsuarioId {
        if (value == null || value <= 0) {
            throw ValidationException.of("El usuario debe ser válido");
        }
    }
}