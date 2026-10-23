package com.finanzas.app.gastos.categorias.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.dto.context.CategoriaResponse;
import com.finanzas.app.shared.presentation.filters.TipoObjetoFilter;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarCategoriasGastosQueryService {

	private final CategoriaGastoRepository repository;
    private final CategoriaGastoApplicationMapper categoriaGastoMapper;

    public List<CategoriaResponse> ejecutar(TipoObjetoFilter filter) {

        List<CategoriaGasto> lista = switch (filter) {
            case SISTEMA -> repository.listarPorTipo(TipoObjeto.SISTEMA);
            case USUARIO -> repository.listarPorTipo(TipoObjeto.USUARIO);
            case TODOS -> repository.listarAll();
        };

        return lista
                .stream()
                .map(categoriaGastoMapper::mapToResponse)
                .toList();
    }
}
