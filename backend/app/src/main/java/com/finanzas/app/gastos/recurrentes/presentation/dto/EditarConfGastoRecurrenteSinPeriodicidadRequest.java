package com.finanzas.app.gastos.recurrentes.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditarConfGastoRecurrenteSinPeriodicidadRequest (

	@Size(max = 20, message = "La descripción debe tener Max 20 caracteres")
	@NotNull
    String descripcion
	
) {}
