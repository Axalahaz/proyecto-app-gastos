package com.finanzas.app.identity.infrastructure;

import org.springframework.stereotype.Component;

import com.finanzas.app.identity.domain.Email;
import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.shared.domain.vo.Fecha;

@Component
public class UsuarioMapper {

    public UsuarioEntityJPA mapToEntity(Usuario usuario) {
    	UsuarioEntityJPA entity = UsuarioEntityJPA.of(
    			usuario.getId(),
    			usuario.getNombreUsuario(),
    	        usuario.getEmail().value(),
    	        usuario.getPasswordHash(),
    	        usuario.isActivo(),
    	        usuario.getFechaCreacion().value(),
    	        
    	        usuario.getFechaModificacion() != null
                        ? usuario.getFechaModificacion().value()
                        : null,
                        
                usuario.getFechaCambioEstado() != null
                        ? usuario.getFechaCambioEstado().value()
                        : null,

                usuario.getFechaCambioPassword() != null
                        ? usuario.getFechaCambioPassword().value()
                        : null,
                        
    	        usuario.getResetPasswordVersion()
    	        );
        return entity;
    }

    public Usuario mapToDomain(UsuarioEntityJPA entity) {
    	return Usuario.reconstruir(
    	        entity.getId(),
    	        entity.getNombreUsuario(),
    	        new Email(entity.getEmail()),
    	        entity.getPasswordHash(),
    	        entity.isActivo(),
    	        new Fecha(entity.getFechaCreacion()),
    	        
    	        entity.getFechaModificacion() != null
                		? new Fecha(entity.getFechaModificacion())
                		: null,
                		
                entity.getFechaCambioEstado() != null
                		? new Fecha(entity.getFechaCambioEstado())
                				: null,
                		
		        entity.getFechaCambioPassword() != null
		                ? new Fecha(entity.getFechaCambioPassword())
		                : null,

		        entity.getResetPasswordVersion()
    	);
    }
}
