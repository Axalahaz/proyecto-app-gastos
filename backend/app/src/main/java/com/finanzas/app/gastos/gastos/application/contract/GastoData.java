package com.finanzas.app.gastos.gastos.application.contract;

import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.UsuarioId;

public record GastoData(
		
    Long id,
    UsuarioId usuarioId,
    Descripcion descripcion,
    CategoriaId categoriaGastoId,
    Long confGastoRecurrenteId
    
) {}