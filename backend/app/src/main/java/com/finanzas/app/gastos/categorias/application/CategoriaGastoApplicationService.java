package com.finanzas.app.gastos.categorias.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

/*
 * Query que agrupa funciones de consulta recurridas por la mayoria de Services
 */

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Component
public class CategoriaGastoApplicationQuery {

    private final CategoriaGastoRepository categoriaRepository;
    
    // -------------------------------------------------------------
 	/*
 	 * Consultas:
 	 * - Consideran el usuario al que pertenece la categoría.
 	 * - Respetan el tipo de objeto: SISTEMA o USUARIO, según corresponda a la operación.
 	 */
 	// -------------------------------------------------------------
    
    public CategoriaGasto obtenerPorId(Long categoriaGastoId, Long usuarioId) {
        return categoriaRepository.buscarPorId(categoriaGastoId, usuarioId)
                .orElseThrow(() -> NotFoundException.of("Categoría", categoriaGastoId));
    }
    
    public void existePorId(Long categoriaGastoId, Long usuarioId) {
    	if (!categoriaRepository.existePorId(categoriaGastoId, usuarioId)) {
            throw NotFoundException.of("Categoría", categoriaGastoId);
        }    
    }
}