package com.finanzas.app.gastos.presentation.dto.categoriaGasto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ActualizarCategoriaGastoRequest {

	@Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;
	
}