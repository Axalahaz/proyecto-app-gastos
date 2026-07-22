package com.finanzas.app.shared.domain.vo;

import java.time.LocalDateTime;

import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

@Getter
public class Fecha {

    private final LocalDateTime value;
    
    // ----------------------------------------------------
    // CONTRUCTOR

    public Fecha(LocalDateTime value) {
    	validarFecha(value);
        this.value = value;
    }
    
    // ----------------------------------------------------
    // COMPARACION
    
    public boolean esAnteriorA(Fecha otra) {
        return this.value.isBefore(otra.value);
    }
    
    // ----------------------------------------------------
    // VALIDACION
    
    private void validarFecha(LocalDateTime value) {
    	if (value == null) {
            throw ValidationException.of("La fecha no puede ser null");
        }
    }
}