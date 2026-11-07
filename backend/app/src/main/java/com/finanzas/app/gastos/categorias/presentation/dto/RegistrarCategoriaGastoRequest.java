package com.finanzas.app.gastos.categorias.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrarCategoriaGastoRequest (
	
	@NotNull(message = "El nombre es obligatorio")
	@Size(max = 50, message = "Máximo 50 caracteres")
    String nombre
	
) {}