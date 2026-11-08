package com.finanzas.app.gastos.categorias.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoApplication;
import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoData;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementa el contrato público del componente categorías,
 * exponiendo las operaciones de aplicación disponibles para
 * la comunicación con otros componentes.
 */
@RequiredArgsConstructor
@Component
public class CategoriaGastoApplicationService implements CategoriaGastoApplication {

	private final CategoriaGastoApplicationQuery categoriaQuery;
    private final CategoriaGastoRepository categoriaRepository;

    // Verifica que la categoría pertenezca al usuario indicado.

    @Override
    @Transactional(readOnly  = true)
	public CategoriaGastoData obtenerDatos(
			Long categoriaGastoId, 
			Long usuarioId
			) {
    	
    	CategoriaGasto categoria = categoriaQuery.obtenerPorId(categoriaGastoId, usuarioId);
		
		CategoriaGastoData data = new CategoriaGastoData(
				categoria.getId(),
				categoria.getUsuarioId(),
				categoria.getNombre()
		);
		
		return data;
	}
    
    @Override
    @Transactional(readOnly = true)
    public boolean existePorNombre(
	        String nombre,
	        Long usuarioId
    		) {
        return categoriaRepository.existePorNombre(nombre, usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public void existePorId(
    		Long categoriaGastoId, 
    		Long usuarioId
    		) {
    	categoriaQuery.existePorId(categoriaGastoId, usuarioId);
    }
}