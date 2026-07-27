package com.finanzas.app.gastos.presentation.dto.gastoRecurrente;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EditarGastoRecurrenteSinPeriodicidadRequest {

	@Size(max = 50, message = "Máximo 50 caracteres")
    private String descripcion;
}
