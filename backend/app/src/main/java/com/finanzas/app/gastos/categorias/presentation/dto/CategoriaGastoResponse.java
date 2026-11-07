package com.finanzas.app.gastos.categorias.presentation.dto;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.enums.TipoObjeto;

public record CategoriaGastoResponse(
    Long id,
    String nombre,
    TipoObjeto tipo,
    LocalDateTime fechaCreacion
) {}