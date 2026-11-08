package com.finanzas.app.gastos.gastos.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistrarGastoDesdeRecurrenteRequest(

    @NotNull(message = "El importe es obligatorio")
    @Positive(message = "El importe debe ser positivo")
    @Digits(integer = 12, fraction = 2, message = "Máximo 12 enteros y 2 decimales")
    BigDecimal importe,

    @NotNull(message = "El id de gasto recurrente es obligatorio")
    @Positive(message = "El id de gasto recurrente debe ser positivo")
    Long confGastoRecurrenteId,

    @NotNull(message = "La fecha de pago recurrente a impactar es obligatoria")
    LocalDateTime fechaPagoRecurrente
    
) {}