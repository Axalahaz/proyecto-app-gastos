package com.finanzas.app.gastos.categorias.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ForbiddenException;
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
public class EliminarTodosPorUsuarioCategoriaGastoCommandService {

    private final CategoriaGastoRepository categoriaRepository;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long usuarioId) {
    	
    	Long authId = usuarioAutenticado.obtenerId();

    	if(usuarioId != authId) {
    		throw new ForbiddenException("No se tiene permiso para eliminar datos");
    	}
    		
    	int contador = categoriaRepository.contadorTotalCategoriaGasto(usuarioId);
        
        int filasEliminadas = categoriaRepository.eliminarDatosDeUsuario(usuarioId);
    	
    	if (filasEliminadas == 0) {
        	throw NotFoundException.of("No se encontron Categorias Gastos para eliminar");
        }
        
        if (filasEliminadas != contador) {
        	throw NotFoundException.of("Eliminacion incompleta");
        }
        
        log.info("Categorias Gastos eliminados correctamente para el usuario {}", usuarioId);
    }
}