package com.finanzas.app.gastos.gastos.presentation.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EditarGastoRequest(

    @Positive(message = "El importe debe ser positivo")
    @Digits(integer = 12, fraction = 2, message = "Máximo 12 enteros y 2 decimales")
    BigDecimal importe,

    @Size(max = 50, message = "Máximo 50 caracteres")
    String descripcion,

    @Positive(message = "El id de categoría de gasto debe ser positivo")
    Long categoriaGastoId

) {}