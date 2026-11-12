package com.finanzas.app.gastos.gastos.presentation.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegistrarGastoRequest(

    @NotNull(message = "El importe es obligatorio")
    @Positive(message = "El importe debe ser positivo")
    @Digits(integer = 12, fraction = 2, message = "Máximo 12 enteros y 2 decimales")
    BigDecimal importe,

    @NotNull(message = "La descripción es obligatoria")
    @Size(max = 20, message = "La descripción debe tener Max 20 caracteres")
    String descripcion,

    @NotNull(message = "La categoría de gasto es obligatoria")
    @Positive(message = "La categoría de gasto debe ser positiva")
    Long categoriaGastoId

) {}