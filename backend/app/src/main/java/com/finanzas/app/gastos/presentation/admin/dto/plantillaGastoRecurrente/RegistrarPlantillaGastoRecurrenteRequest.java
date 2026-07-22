package com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrarPlantillaGastoRecurrenteRequest {
	
	@NotNull(message = "La descripcion es obligatorio")
	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion;
	
	@NotNull(message = "El estado de activacion es obligatorio")
    private boolean activo;
}
