package com.finanzas.app.gastos.categorias.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Component
public class CategoriaGastoFinder {

    private final CategoriaGastoRepository categoriaRepository;

    public CategoriaGasto obtenerPorId(Long id) {
        return categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> NotFoundException.of("Categoría", id));
    }
    
    public void validarExistencia(Long id) {
    	if (!categoriaRepository.existePorId(id)) {
            throw NotFoundException.of("Categoría", id);
        }
    }
}