package com.finanzas.app.gastos.categorias.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoApplication;
import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoData;
import com.finanzas.app.gastos.categorias.application.service.write.EliminarTodosPorUsuarioCategoriaGastoCommandService;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementa el contrato público del componente categorías,
 * exponiendo las operaciones de aplicación disponibles para
 * la comunicación con otros componentes.
 */
@RequiredArgsConstructor
@Service
public class CategoriaGastoApplicationService implements CategoriaGastoApplication {

	private final CategoriaGastoApplicationQuery categoriaGastoApplicationQuery;
    private final CategoriaGastoRepository categoriaGastoRepository;
    
	private final EliminarTodosPorUsuarioCategoriaGastoCommandService eliminarDatosDeUsuarioService;

    // Verifica que la categoría pertenezca al usuario indicado.

    @Override
    @Transactional(readOnly  = true)
	public CategoriaGastoData obtenerDatos(
			Long categoriaGastoId, 
			Long usuarioId
			) {
    	
    	CategoriaGasto categoria = categoriaGastoApplicationQuery.obtenerPorId(categoriaGastoId, usuarioId);
		
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
        return categoriaGastoRepository.existePorNombre(nombre, usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public void existePorId(
    		Long categoriaGastoId, 
    		Long usuarioId
    		) {
    	categoriaGastoApplicationQuery.existePorId(categoriaGastoId, usuarioId);
    }
    
    @Override
    @Transactional
    public void eliminarDatosDeUsuario(Long usuarioId) {
    	eliminarDatosDeUsuarioService.ejecutar(usuarioId);
    }
}