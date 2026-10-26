package com.finanzas.app.gastos.categorias.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationQuery;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

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
public class EliminarCategoriaGastoCommandService {

	private final CategoriaGastoApplicationQuery categoriaQuery;
    private final CategoriaGastoRepository categoriaRepository;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(
    		Long categoriaId
    		) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();

    	CategoriaGasto categoria = categoriaQuery.obtenerPorId(categoriaId, usuarioId);
    
    	if(categoria.esTipoSistema()) {
    		throw new ConflictException(
    				"No se puede eliminar una Categoria de Gasto propia del sistema");
    	}

    	int filasEliminadas = categoriaRepository.eliminar(categoria.getId(), usuarioId);
    	
    	if(filasEliminadas == 0) {
    		throw NotFoundException.of("No se pudo eliminar la categoria");
    	}
        
        log.info("Categoria Gasto {} eliminado correctamente", categoria.getId());
    }
}