package com.finanzas.app.gastos.presentation.dto.plantillaGasto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EditarPlantillaGastoRequest {
	
	private Long categoriaGastoId;

	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion;
	
}