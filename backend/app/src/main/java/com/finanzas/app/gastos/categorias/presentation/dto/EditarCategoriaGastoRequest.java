package com.finanzas.app.gastos.categorias.presentation.dto;

import jakarta.validation.constraints.Size;

public record EditarCategoriaGastoRequest (
	
	@Size(max = 50, message = "Máximo 50 caracteres")
    String nombre
	
) {}