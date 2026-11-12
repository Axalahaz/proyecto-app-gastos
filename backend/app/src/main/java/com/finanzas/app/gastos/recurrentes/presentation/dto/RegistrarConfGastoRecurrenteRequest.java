package com.finanzas.app.gastos.recurrentes.presentation.dto;

import com.finanzas.app.shared.domain.enums.Frecuencia;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegistrarConfGastoRecurrenteRequest(

	@NotNull
    Long categoriaGastoId,

    @NotNull(message = "La descripción es obligatoria")
	@Size(max = 20, message = "La descripción debe tener Max 20 caracteres")
    String descripcion,

    @Positive(message = "El día de vencimiento debe ser positivo")
    @Min(1)
    @Max(31)
    Integer diaVencimiento,

    @NotNull(message = "El mes de vencimiento es obligatorio")
    @Positive(message = "El mes de vencimiento debe ser positivo")
    @Min(1)
    @Max(12)
    Integer mesVencimiento,

    @NotNull(message = "La frecuencia es obligatoria")
    Frecuencia frecuencia

) {}