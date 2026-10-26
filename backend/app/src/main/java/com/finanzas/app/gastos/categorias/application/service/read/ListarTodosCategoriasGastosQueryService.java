package com.finanzas.app.gastos.categorias.application.service.read;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.gastos.categorias.presentation.dto.CategoriaGastoResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;

/*
 *	LISTA TODOS LAS CATEGORIAS DEL USUARIO
 * INCLUYENDO LAS DEL SISTEMA QUE SON UNIVERSALES. 
 */

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarTodosCategoriasGastosQueryService {

	private final CategoriaGastoRepository repository;
    private final CategoriaGastoApplicationMapper categoriaGastoMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public List<CategoriaGastoResponse> ejecutar() {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
    	List<CategoriaGasto> lista = repository.listarTodos(usuarioId);

        return lista
        		.stream()
                .map(categoriaGastoMapper::mapToResponse)
                .toList();
    }
}
