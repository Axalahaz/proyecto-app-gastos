package com.finanzas.app.gastos.recurrentes.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ForbiddenException;

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
    	
    	if(!usuarioId.equals(authId)) {
    		throw new ForbiddenException("No se tiene permiso para eliminar datos");
    	}
    		
        int filasEliminadas = confGastoRecurrenteRepository.eliminarDatosDeUsuario(usuarioId);
        
        log.info("{} Configuraciones Gastos Recurrentes eliminados correctamente para el usuario {}", filasEliminadas, usuarioId);
    }
}