package com.finanzas.app.shared.domain.vo;

import java.math.BigDecimal;

import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

public record Money(BigDecimal value) {

	public Money {
    	if (value == null) {
            throw ValidationException.of("El monto no puede ser null");
        }
    	
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw ValidationException.of("El monto debe ser mayor a cero");
        }
    }
}