package com.finanzas.app.gastos.categorias.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.shared.dto.context.CategoriaResponse;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarTodosCategoriasGastosQueryService {

	private final CategoriaGastoRepository repository;
    private final CategoriaGastoApplicationMapper categoriaGastoMapper;

    public List<CategoriaResponse> ejecutar() {

    	List<CategoriaGasto> lista = repository.listarAll();

        return lista
        		.stream()
                .map(categoriaGastoMapper::mapToResponse)
                .toList();
    }
}
