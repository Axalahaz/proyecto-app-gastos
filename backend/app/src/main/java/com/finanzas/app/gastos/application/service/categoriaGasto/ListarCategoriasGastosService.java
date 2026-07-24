package com.finanzas.app.gastos.application.service.categoriaGasto;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.shared.dto.context.CategoriaResponse;
import com.finanzas.app.shared.presentation.filters.TipoObjetoFilter;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarCategoriasGastosService {

	private final CategoriaGastoRepository repository;
    private final CategoriaGastoApplicationMapper categoriaGastoMapper;

    public List<CategoriaResponse> ejecutar(TipoObjetoFilter filter) {

    	List<CategoriaGasto> lista = (filter == TipoObjetoFilter.TODOS)
                ? repository.listarAll()
                : repository.listarPorTipo(filter.toTipoObjeto());

        return lista
        		.stream()
                .map(categoriaGastoMapper::mapToResponse)
                .toList();
    }
}
