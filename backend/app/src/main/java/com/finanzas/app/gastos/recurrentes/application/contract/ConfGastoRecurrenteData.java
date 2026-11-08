package com.finanzas.app.gastos.recurrentes.application.contract;

import com.finanzas.app.gastos.recurrentes.domain.EstadoConf;
import com.finanzas.app.shared.domain.enums.Frecuencia;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.UsuarioId;

public record ConfGastoRecurrenteData(
		
    Long id,
    UsuarioId usuarioId,
    Descripcion descripcion,
    CategoriaId categoriaGastoId,
    Frecuencia frecuencia,
    Integer diaVencimiento,
    Integer mesVencimiento,
    EstadoConf estado
    
) {}