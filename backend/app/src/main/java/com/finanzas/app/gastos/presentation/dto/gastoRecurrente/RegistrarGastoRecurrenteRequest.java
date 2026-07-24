package com.finanzas.app.gastos.presentation.dto.gastoRecurrente;

import com.finanzas.app.shared.domain.model.Frecuencia;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrarGastoRecurrenteRequest {
	
    private Long plantillaGastoRecurrenteId;
	
	@NotNull(message = "La descripcion es obligatorio")
	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion;
	
	@NotNull(message = "La dia de vto es obligatoria")
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
