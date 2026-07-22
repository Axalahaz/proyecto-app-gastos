package com.finanzas.app.shared.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

@Getter
public class Money {

    private final BigDecimal value;
    
    // ----------------------------------------------------
    // CONTRUCTOR

    public Money(BigDecimal value) {
    	validarMoney(value);
        this.value = value;
    }
    
    // ----------------------------------------------------
    // COMPARACION
    
    // igualdad por valor (VO)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        
        Money money = (Money) o;
        return this.value.compareTo(money.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    // ----------------------------------
    // METODOS PRIVADOS 
    // ----------------------------------
    
    // ----------------------------------------------------
    // Validacion
    
    private void validarMoney(BigDecimal value) {
    	if (value == null) {
            throw ValidationException.of("El monto no puede ser null");
        }
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw ValidationException.of("El monto debe ser mayor a cero");
        }
    }
}