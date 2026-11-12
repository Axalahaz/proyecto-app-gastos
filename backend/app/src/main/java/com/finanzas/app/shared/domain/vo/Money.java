package com.finanzas.app.shared.domain.vo;

import java.math.BigDecimal;

import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

public record Money(BigDecimal value) {

	private static final int MAX_INTEGER = 12;
    private static final int MAX_FRACTION = 2;
    
	public Money {
    	if (value == null) {
            throw ValidationException.of("El monto no puede ser null");
        }
    	
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw ValidationException.of("El monto debe ser mayor a cero");
        }
        
        int integerDigits = Math.max(0, value.precision() - value.scale());
        int fractionDigits = Math.max(0, value.scale());

        if (integerDigits > MAX_INTEGER || fractionDigits > MAX_FRACTION) {
            throw ValidationException.of(
                "El monto debe tener máximo 12 enteros y 2 decimales"
            );
        }
    }
}