package com.finanzas.app.identity.infrastructure;

import org.springframework.stereotype.Component;

import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.infrastructure.entity.UsuarioEntityJPA;
import com.finanzas.app.shared.domain.vo.Fecha;

@Component
public class UsuarioMapper {

    public UsuarioEntityJPA mapToEntity(Usuario usuario) {
    	UsuarioEntityJPA entity = UsuarioEntityJPA.of(
    			usuario.getId(),
    	        usuario.getEmail(),
    	        usuario.getPassword(),
    	        usuario.isActivo(),
    	        usuario.getFechaCreacion().getValue(),
    	        
    	        usuario.getFechaModificacion() != null
                        ? usuario.getFechaModificacion().getValue()
                        : null,
                        
    	        usuario.getFechaCambioEstado() != null
                        ? usuario.getFechaCambioEstado().getValue()
                        : null,

                usuario.getFechaCambioPassword() != null
                        ? usuario.getFechaCambioPassword().getValue()
                        : null,
                        
    	        usuario.getResetPasswordVersion()
    	        );
        return entity;
    }

    public Usuario mapToDomain(UsuarioEntityJPA entity) {
    	return Usuario.reconstruir(
    	        entity.getId(),
    	        entity.getEmail(),
    	        entity.getPassword(),
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
