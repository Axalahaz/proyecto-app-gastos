package com.finanzas.app.gastos.gastos.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GastoResponse(
    Long id,
    Long usuarioId,
    BigDecimal importe,
    String descripcion,
    LocalDateTime fechaCreacion,
    LocalDateTime fechaModificacion,
    LocalDateTime fechaPagoRecurrente,
    String estado,
    Long categoriaGastoId,
    Long confGastoRecurrenteId
) {}