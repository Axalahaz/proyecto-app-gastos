package com.finanzas.app.gastos.presentation.dto.plantillaGasto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrarPlantillaGastoRequest {
	
	@NotNull(message = "La categoria de gasto es obligatoria")
	@Positive(message = "La categoria de gasto debe ser positiva")
    private Long categoriaGastoId;
	
	@NotNull(message = "El nombre es obligatorio")
	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion;
	
}