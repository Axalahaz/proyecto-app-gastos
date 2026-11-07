package com.finanzas.app.gastos.recurrentes.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditarConfGastoRecurrenteSinPeriodicidadRequest (

	@Size(max = 50, message = "Máximo 50 caracteres")
	@NotNull
    String descripcion
	
) {}
