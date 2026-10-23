package com.finanzas.app.gastos.categorias.application;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
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
