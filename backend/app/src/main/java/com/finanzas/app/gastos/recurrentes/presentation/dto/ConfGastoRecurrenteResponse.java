package com.finanzas.app.gastos.recurrentes.presentation.dto;

import java.time.LocalDateTime;

public record ConfGastoRecurrenteResponse(
    Long id,
    Long usuarioId,
    String descripcion,
    Long categoriaGastoId,
    String frecuencia,
    Integer diaVencimiento,
    Integer mesVencimiento,
    String estado,
    LocalDateTime fechaCreacion,
    LocalDateTime fechaModificacion
) {}