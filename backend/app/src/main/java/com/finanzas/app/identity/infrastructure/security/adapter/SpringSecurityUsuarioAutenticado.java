package com.finanzas.app.identity.infrastructure.security.adapter;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.NoAutenticadoException;

/*
 * ADAPTADOR QUE PERMITE QUE LOS CU OBTENGAN EL ID DEL USUARIO AUTENTICADO 
 * SIN DEPENDER DE SPRINGSECURITY 
 */

@Component
public class SpringSecurityUsuarioAutenticado implements UsuarioAutenticado  {
    
    @Override
    public Long obtenerId() {

    	// Recupera el usuario autenticado desde el contexto de seguridad
    	UserDetailsAdapter authPrincipal = obtenerAuthPrincipal();
    	
    	// Se retorna el ID del usuario como username (String -> Long)
    	return Long.valueOf(authPrincipal.getUsername()); 
    }
    
    // ------------------------------------------------------------------
    // obtenerAuthPrincipal()
    // ------------------------------------------------------------------
    
    private UserDetailsAdapter obtenerAuthPrincipal() {
    
    	/*
    	 * SecurityContextHolder: permite acceder al contexto de seguridad asociado a la ejecución actual.
    	 * getContext(): obtiene ese contexto.
    	 * getAuthentication(:) recupera la información de autenticación 
    	 * 	que Spring tiene registrada por 
    	 * 	{@Link package com.finanzas.app.identity.infrastructure.security.jwt.JwtAuthenticationFilter}.
    	 */
        Authentication auth = SecurityContextHolder
        		.getContext()
        		.getAuthentication();
        
        // Comprobar que exista una autenticación válida.
        if (auth == null || !auth.isAuthenticated()
        		|| auth instanceof AnonymousAuthenticationToken) {
            throw new NoAutenticadoException("Usuario no autenticado");
        }
        
        // Usuario autenticado
        Object principal = auth.getPrincipal();
        
        // Verifica si el tipo corresponde a lo implementado
        if (principal instanceof UserDetailsAdapter adaptador) {
        	return adaptador;
        }
        
        throw new NoAutenticadoException("Usuario no autenticado");
    }
}