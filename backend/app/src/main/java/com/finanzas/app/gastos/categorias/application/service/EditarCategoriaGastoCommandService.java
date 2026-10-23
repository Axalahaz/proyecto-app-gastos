package com.finanzas.app.gastos.categorias.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.application.CategoriaGastoFinder;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.shared.dto.context.CategoriaResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EditarCategoriaGastoCommandService {

	private final CategoriaGastoFinder categoriaFinder;

    private final CategoriaGastoRepository categoriaRepository;
    private final CategoriaGastoApplicationMapper categoriaMapper;

    public CategoriaResponse ejecutar(
    		Long categoriaId,
    		String nombre
    ) {

    	CategoriaGasto categoria = categoriaFinder.obtenerPorId(categoriaId);

    	categoria.renombrar(nombre);
    	
    	CategoriaGasto guardada = categoriaRepository.guardar(categoria);
        
        log.info("Categoria Gasto editado correctamente");
        
        return categoriaMapper.mapToResponse(guardada);
    }
}