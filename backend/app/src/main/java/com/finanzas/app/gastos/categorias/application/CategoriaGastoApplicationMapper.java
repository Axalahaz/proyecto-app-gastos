package com.finanzas.app.gastos.categorias.application;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.presentation.dto.CategoriaGastoResponse;

@Component
public class CategoriaGastoApplicationMapper {

    public CategoriaGastoResponse mapToResponse(CategoriaGasto categoriaGasto) {
    	return CategoriaGastoResponse.of(
    			categoriaGasto.getId(),
    			categoriaGasto.getNombre(),
    			categoriaGasto.getTipo(),
    			categoriaGasto.getFechaCreacion().getValue()
    			);
    }
}
