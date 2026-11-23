package com.finanzas.app.gastos.categorias.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrarCategoriaGastoRequest (
	
	@NotNull(message = "El nombre es obligatorio")
	@Size(max = 20, message = "Máximo 20 caracteres")
    String nombre
	
) {}