package com.finanzas.app.gastos.application.service.categoriaGasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.CategoriaGastoQueryService;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.shared.dto.context.CategoriaResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EditarCategoriaGastoService {

	private final CategoriaGastoQueryService categoriaQueryService;

    private final CategoriaGastoRepository categoriaRepository;
    private final CategoriaGastoApplicationMapper categoriaMapper;

    public CategoriaResponse ejecutar(
    		Long categoriaId,
    		String nombre
    ) {

    	CategoriaGasto categoria = categoriaQueryService.obtenerPorId(categoriaId);

    	categoria.renombrar(nombre);
    	
    	CategoriaGasto guardada = categoriaRepository.guardar(categoria);
        
        log.info("Categoria Gasto editado correctamente");
        
        return categoriaMapper.mapToResponse(guardada);
    }
}