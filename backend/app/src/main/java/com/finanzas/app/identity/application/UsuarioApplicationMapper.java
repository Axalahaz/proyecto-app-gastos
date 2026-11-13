package com.finanzas.app.identity.application;

import org.springframework.stereotype.Component;

import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.presentation.dto.UsuarioResponse;

@Component
public class UsuarioApplicationMapper {

    public UsuarioResponse mapToResponse(Usuario usuario) {
    	
    	return new UsuarioResponse(
        		usuario.getId(),
        		usuario.getNombreUsuario(),
        		usuario.getEmail().value(),
        		usuario.isActivo(),
        		usuario.getFechaCreacion().value(),
        		
        		usuario.getFechaModificacion() != null
	    			? usuario.getFechaModificacion().value()
	    			: null,

	    		usuario.getFechaCambioPassword() != null
	                ? usuario.getFechaCambioPassword().value()
	                : null
        		);
    }
}
