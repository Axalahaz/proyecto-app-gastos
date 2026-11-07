package com.finanzas.app.shared.domain.vo;

import java.time.LocalDateTime;

import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

public record Fecha(LocalDateTime value) {

    public Fecha {
    	if (value == null) {
            throw ValidationException.of("La fecha es obligatoria");
        }
    }
    
    // ----------------------------------------------------
    // COMPARACION
    
    public boolean esAnteriorA(Fecha otra) {
    	if (otra == null) {
            throw ValidationException.of("La fecha de comparación es obligatoria");
        }
    	
        return this.value.isBefore(otra.value);
    }
    
    public boolean esPosteriorA(Fecha otra) {
        if (otra == null) {
            throw ValidationException.of("La fecha de comparación es obligatoria");
        }

        return this.value.isAfter(otra.value);
    }
}