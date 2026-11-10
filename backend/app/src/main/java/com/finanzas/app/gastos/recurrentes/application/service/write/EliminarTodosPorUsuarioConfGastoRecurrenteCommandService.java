package com.finanzas.app.gastos.recurrentes.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ForbiddenException;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarTodosPorUsuarioConfGastoRecurrenteCommandService {
	
    private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long usuarioId) {

    	Long authId = usuarioAutenticado.obtenerId();
    	
    	if(usuarioId != authId) {
    		throw new ForbiddenException("No se tiene permiso para eliminar datos");
    	}
    		
    	int contador = confGastoRecurrenteRepository.contadorTotalConfGastoRecurrente(usuarioId);
        
        int filasEliminadas = confGastoRecurrenteRepository.eliminarDatosDeUsuario(usuarioId);
    	
    	if (filasEliminadas == 0) {
        	throw NotFoundException.of("No se encontron Configuraciones Recurrentes para eliminar");
        }
        
        if (filasEliminadas != contador) {
        	throw NotFoundException.of("Eliminacion incompleta");
        }
        
        log.info("Configuraciones Gastos Recurrentes eliminados correctamente para el usuario {}", usuarioId);
    }
}