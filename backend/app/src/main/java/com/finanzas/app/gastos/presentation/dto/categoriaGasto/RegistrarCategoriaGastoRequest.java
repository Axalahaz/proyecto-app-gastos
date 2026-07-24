package com.finanzas.app.gastos.presentation.dto.categoriaGasto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrarCategoriaGastoRequest {
	
	@NotNull(message = "El nombre es obligatorio")
	@Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;
	
}