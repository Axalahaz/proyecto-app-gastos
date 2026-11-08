package com.finanzas.app.gastos.categorias.application.contract;

import com.finanzas.app.shared.domain.vo.UsuarioId;

public record CategoriaGastoData(
    Long id,
    UsuarioId usuarioId,
    String nombre
    
) {}