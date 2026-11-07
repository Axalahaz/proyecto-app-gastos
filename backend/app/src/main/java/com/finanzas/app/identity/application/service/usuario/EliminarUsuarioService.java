package com.finanzas.app.identity.application.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.context.ApplicationEventPublisher; // eventos

import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.event.UsuarioEliminadoEvent;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class EliminarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    
    private final ApplicationEventPublisher eventPublisher;

    public void ejecutar() {

        Long userId = usuarioAutenticado.obtenerId();

        int filasEliminadas = usuarioRepository.eliminar(userId);
        
        if(filasEliminadas == 0) {
    		throw NotFoundException.of("No se pudo eliminar el Usuario");
    	}
        
        eventPublisher.publishEvent(new UsuarioEliminadoEvent(userId));
        
        /*
         * Este evento despues deberia desencadenar en cascada un borrado de informacion:
         * -gastos
         * -categoria
         * - etc
         */
    }
}