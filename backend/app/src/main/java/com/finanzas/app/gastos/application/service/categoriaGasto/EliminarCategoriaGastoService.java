package com.finanzas.app.gastos.application.service.categoriaGasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Permite eliminar fisicamente un error durante un tiempo limitado
 * Despues solo se puede anular
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarCategoriaGastoService {

    private final CategoriaGastoRepository categoriaRepository;

    public void ejecutar(Long categoriaId) {

    	CategoriaGasto categoria = categoriaRepository
                .buscarPorId(categoriaId)
                .orElseThrow(() ->
                        NotFoundException.of("Categoria Gasto", categoriaId));
        
    	if(categoria.esTipo(TipoObjeto.SISTEMA)) {
    		throw new ConflictException("No se puede eliminar una categoria del sistema");
    	}

    	categoriaRepository.eliminar(categoria.getId());
        
        log.info("Categoria Gasto {} eliminado correctamente", categoria.getId());
    }
}