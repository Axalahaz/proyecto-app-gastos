package com.finanzas.app.gastos.presentation.dto.gasto;

import com.finanzas.app.shared.domain.model.Frecuencia;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class VolverRecurrenteRequest {

	@NotNull(message = "El dia de vto es obligatoria")
	@Positive(message = "La dia de vto debe ser positivo")
	@Min(1)
	@Max(31)
    private Integer diaVencimiento;

	@Positive(message = "El mes de vto debe ser positivo")
	@Min(1)
	@Max(12)
	private Integer mesVencimiento;
	
	@NotNull(message = "La frecuencia de vto es obligatoria")
    private Frecuencia frecuencia;
}