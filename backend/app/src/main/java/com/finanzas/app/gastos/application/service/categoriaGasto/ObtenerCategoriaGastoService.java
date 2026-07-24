package com.finanzas.app.gastos.application.service.categoriaGasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.CategoriaGastoQueryService;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.shared.dto.context.CategoriaResponse;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerCategoriaGastoService {

	private final CategoriaGastoQueryService categoriaQueryService;
    private final CategoriaGastoApplicationMapper applicationMapper;

    public CategoriaResponse ejecutar(Long categoriaGastoId) {
    	
    	CategoriaGasto categoria = categoriaQueryService.obtenerPorId(categoriaGastoId);
	    		
    	return applicationMapper.mapToResponse(categoria);
    }
}
