package com.finanzas.app.gastos.gastos.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ForbiddenException;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Se ejecuta al eliminarse un usuario fisicamente
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarTodosPorUsuarioGastoCommandService {

    private final GastoRepository gastoRepository;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long usuarioId) {

    	Long authId = usuarioAutenticado.obtenerId();
    	
    	if(usuarioId != authId) {
    		throw new ForbiddenException("No se tiene permiso para eliminar datos");
    	}
    		
        int contador = gastoRepository.contadorTotalGastos(usuarioId);
        
        int filasEliminadas = gastoRepository.eliminarDatosDeUsuario(usuarioId);
        
        if (filasEliminadas == 0) {
        	throw NotFoundException.of("No se encontron Gastos para eliminar");
        }
        
        if (filasEliminadas != contador) {
        	throw NotFoundException.of("Eliminacion incompleta");
        }
        
        log.info("Gastos eliminados correctamente para el usuario {}", usuarioId);
    }
}