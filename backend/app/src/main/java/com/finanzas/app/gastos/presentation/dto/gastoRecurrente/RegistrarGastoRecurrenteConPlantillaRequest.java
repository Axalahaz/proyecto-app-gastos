package com.finanzas.app.gastos.presentation.dto.gastoRecurrente;

import com.finanzas.app.shared.domain.model.Frecuencia;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class RegistrarGastoRecurrenteConPlantillaRequest {
	
	@NotNull(message = "La plantilla de recurrente es obligatoria")
    private Long plantillaGastoRecurrenteId;
	
	@NotNull(message = "La dia de vto es obligatori")
    @Positive(message = "La dia de vto debe ser positivo")
	@Min(1)
	@Max(31)
    private Integer diaVencimiento;

	@Positive(message = "La mes de vto debe ser positivo")
	@Min(1)
	@Max(12)
	private Integer mesVencimiento;
	
	@NotNull(message = "La frecuencia es obligatoria")
    private Frecuencia frecuencia;
}
