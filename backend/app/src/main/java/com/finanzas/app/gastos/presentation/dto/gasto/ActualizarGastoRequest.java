package com.finanzas.app.gastos.presentation.dto.gasto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ActualizarGastoRequest {

	@Positive(message = "El monto debe ser positivo")
	@Digits(integer = 10, fraction = 2, message = "Máximo 10 enteros y 2 decimales")
	private BigDecimal monto;
	
	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion;
	
	@Positive(message = "El id categoria de gasto debe ser positivo")
    private Long categoriaGastoId;
}