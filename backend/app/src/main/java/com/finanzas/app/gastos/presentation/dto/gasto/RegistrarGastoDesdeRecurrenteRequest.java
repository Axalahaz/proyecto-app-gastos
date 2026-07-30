package com.finanzas.app.gastos.presentation.dto.gasto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class RegistrarGastoDesdeRecurrenteRequest {
	
	@NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
	@Digits(integer = 10, fraction = 2, message = "Máximo 10 enteros y 2 decimales")
    private BigDecimal monto;

	@NotNull(message = "El id de gasto recurrente es obligatorio")
	@Positive(message = "El id de gasto recurrente debe ser positivo")
    private Long gastoRecurrenteId; 
	
	@NotNull(message = "La categoria de gasto es obligatoria")
	@Positive(message = "La categoria de gasto debe ser positiva")
    private Long categoriaGastoId;

	@NotNull(message = "La fecha de Vencimiento a impactar es obligatoria")
	private LocalDateTime fechaVencimiento; 
}