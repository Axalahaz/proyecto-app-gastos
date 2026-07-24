package com.finanzas.app.gastos.application.queryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Service
public class CategoriaGastoQueryService {

    private final CategoriaGastoRepository categoriaRepository;

    public CategoriaGasto obtenerPorId(Long id) {
        return categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> NotFoundException.of("Categoría", id));
    }
    
    public void existePorId(Long id) {
    	if (!categoriaRepository.existePorId(id)) {
            throw NotFoundException.of("Categoría", id);
        }
    }
}