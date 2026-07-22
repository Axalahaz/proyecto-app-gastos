package com.finanzas.app.gastos.application.mapper;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.shared.dto.context.CategoriaResponse;

@Component
public class CategoriaGastoApplicationMapper {

    public CategoriaResponse mapToResponse(CategoriaGasto categoriaGasto) {
    	return CategoriaResponse.of(
    			categoriaGasto.getId(),
    			categoriaGasto.getNombre()
    			);
    }
}
