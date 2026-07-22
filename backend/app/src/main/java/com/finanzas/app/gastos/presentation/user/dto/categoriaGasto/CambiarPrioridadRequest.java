package com.finanzas.app.gastos.presentation.user.dto.categoriaGasto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class CambiarPrioridadRequest {

    @NotNull
    @PositiveOrZero
    private Integer prioridad;
}