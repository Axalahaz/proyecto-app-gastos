package com.finanzas.app.shared.infrastructure.security.adapter;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.finanzas.app.identity.application.exception.NoAutenticadoException;
import com.finanzas.app.shared.domain.UsuarioAutenticado;

@Component
public class SpringSecurityUsuarioAutenticado  
        implements UsuarioAutenticado  {

    
    @Override
    public Long obtenerId() {

    	UserDetailsAdapter authPrincipal = obtenerAuthPrincipal();
    	
    	return Long.valueOf(authPrincipal.getUsername()); // es id
    }
    
    // ------------------------------------------------------------------
    // PRIVADO
    
    private UserDetailsAdapter obtenerAuthPrincipal() {
    
        Authentication auth = SecurityContextHolder
        		.getContext()
        		.getAuthentication();
        
        if (auth == null || !auth.isAuthenticated()
        		|| auth instanceof AnonymousAuthenticationToken) {
            throw new NoAutenticadoException("Usuario no autenticado");
        }
        
        Object principal = auth.getPrincipal();
        
        if (principal instanceof UserDetailsAdapter adaptador) {
        	return adaptador;
        }
        
        throw new NoAutenticadoException("Usuario no autenticado");
    }
}