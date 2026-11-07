package com.finanzas.app.gastos.recurrentes.presentation.dto;

import com.finanzas.app.shared.domain.enums.Frecuencia;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record EditarPeriodicidadRequest(

    @Positive(message = "El día de vencimiento debe ser positivo")
    @Min(1)
    @Max(31)
    Integer diaVencimiento,

    @Positive(message = "El mes de vencimiento debe ser positivo")
    @Min(1)
    @Max(12)
    Integer mesVencimiento,

    Frecuencia frecuencia

) {}