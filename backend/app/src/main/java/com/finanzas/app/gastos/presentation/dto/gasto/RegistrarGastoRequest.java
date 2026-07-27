package com.finanzas.app.gastos.presentation.dto.gasto;


import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrarGastoRequest {
	
	@NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
	@Digits(integer = 10, fraction = 2, message = "Máximo 10 enteros y 2 decimales")
    private BigDecimal monto;
    
	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion; // puede venir vacio
    
	@NotNull(message = "La categoria de gasto es obligatoria")
	@Positive(message = "La categoria de gasto debe ser positiva")
    private Long categoriaGastoId;
	
    private Long plantillaId;

}