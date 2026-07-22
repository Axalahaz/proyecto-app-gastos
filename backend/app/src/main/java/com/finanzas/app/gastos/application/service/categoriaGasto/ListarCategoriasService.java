package com.finanzas.app.gastos.application.service.categoriaGasto;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.dto.context.CategoriaResponse;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarCategoriasService {

	private final CategoriaGastoRepository repository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final CategoriaGastoApplicationMapper categoriaGastoMapper;

    public List<CategoriaResponse>  ejecutar() {

    	List<CategoriaGasto> lista;
    	
    	// ADMIN
    	if (usuarioAutenticado.esAdmin()) {
    		lista = repository.listarPorTipo(TipoObjeto.SISTEMA);
        } else {
        	// USER
        	Long userId = usuarioAutenticado.obtenerId();
        	
        	lista = Stream.concat(
        	        repository.listarPorUsuario(userId).stream(),
        	        repository.listarPorTipo(TipoObjeto.SISTEMA).stream()
        	).toList();
        }
    	

        return lista
        		.stream()
                .map(categoriaGastoMapper::mapToResponse)
                .toList();
    }
}
