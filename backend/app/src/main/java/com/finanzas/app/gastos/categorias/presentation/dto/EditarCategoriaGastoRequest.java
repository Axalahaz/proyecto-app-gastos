package com.finanzas.app.gastos.categorias.presentation.dto;

import jakarta.validation.constraints.Size;

public record EditarCategoriaGastoRequest (
	
	@Size(max = 20, message = "Máximo 20 caracteres")
    String nombre
	
) {}