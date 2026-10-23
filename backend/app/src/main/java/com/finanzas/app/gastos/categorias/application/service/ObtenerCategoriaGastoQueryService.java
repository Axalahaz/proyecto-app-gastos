package com.finanzas.app.gastos.categorias.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.application.CategoriaGastoQueryService;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.shared.dto.context.CategoriaResponse;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerCategoriaGastoQueryService {

	private final CategoriaGastoQueryService categoriaQueryService;
    private final CategoriaGastoApplicationMapper applicationMapper;

    public CategoriaResponse ejecutar(Long categoriaGastoId) {
    	
    	CategoriaGasto categoria = categoriaQueryService.obtenerPorId(categoriaGastoId);
	    		
    	return applicationMapper.mapToResponse(categoria);
    }
}
