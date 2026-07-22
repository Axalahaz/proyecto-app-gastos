package com.finanzas.app.gastos.presentation.user.dto.gastoRecurrente;


import com.finanzas.app.gastos.domain.entity.Frecuencia;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ActualizarPeriodicidadRequest {

    @Positive(message = "La dia de vto debe ser positivo")
	@Min(1)
	@Max(31)
    private Integer diaVencimiento;

    @Positive(message = "La dia de vto debe ser positivo")
	@Min(1)
	@Max(12)
    private Integer mesVencimiento;

    private Frecuencia frecuencia;
}
