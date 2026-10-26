package com.finanzas.app.gastos.categorias.application.service.read;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationQuery;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.presentation.dto.CategoriaGastoResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerCategoriaGastoQueryService {

	private final CategoriaGastoApplicationQuery categoriaQuery;
    private final CategoriaGastoApplicationMapper applicationMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public CategoriaGastoResponse ejecutar(Long categoriaGastoId) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
    	CategoriaGasto categoria = categoriaQuery.obtenerPorId(categoriaGastoId, usuarioId);
	    		
    	return applicationMapper.mapToResponse(categoria);
    }
}
