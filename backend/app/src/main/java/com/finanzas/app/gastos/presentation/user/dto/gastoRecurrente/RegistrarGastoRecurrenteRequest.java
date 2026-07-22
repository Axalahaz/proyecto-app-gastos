package com.finanzas.app.gastos.presentation.user.dto.gastoRecurrente;

import com.finanzas.app.gastos.domain.entity.Frecuencia;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrarGastoRecurrenteRequest {
	
	@NotNull(message = "La descripcion es obligatorio")
	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion;
	
	@NotNull(message = "El categoria de gasto es obligatorio")
    private Long categoriaGastoId;
	
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
