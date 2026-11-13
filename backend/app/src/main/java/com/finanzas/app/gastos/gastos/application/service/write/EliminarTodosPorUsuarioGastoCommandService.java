package com.finanzas.app.gastos.gastos.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ForbiddenException;

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
    	
    	if(!usuarioId.equals(authId)) {
    		throw new ForbiddenException("No se tiene permiso para eliminar datos");
    	}
    	
    	log.info("Iniciando eliminación de Gastos para el usuario {}", usuarioId);
    	
        int filasEliminadas = gastoRepository.eliminarDatosDeUsuario(usuarioId);

        log.info("{} Gastos eliminados correctamente para el usuario {}", filasEliminadas, usuarioId);
    }
}