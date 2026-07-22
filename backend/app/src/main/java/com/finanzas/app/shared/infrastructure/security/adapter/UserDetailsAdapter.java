package com.finanzas.app.shared.infrastructure.security.adapter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finanzas.app.identity.domain.entity.Usuario;

import lombok.RequiredArgsConstructor;

/*
 * Adaptador de insfraestructura 
 * 
 * spring no usa directamente usuariodomain
 * 
 * */
@RequiredArgsConstructor
public class UserDetailsAdapter implements UserDetails {

    private final Usuario usuario;
    
    public LocalDateTime getFechaCambioPassword() {
        return usuario.getFechaCambioPassword() != null
                ? usuario.getFechaCambioPassword().getValue()
                        : null;
    }
    
    public LocalDateTime getFechaCambioEstado() {
        return usuario.getFechaCambioEstado() != null
                ? usuario.getFechaCambioEstado().getValue()
                        : null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(usuario.getId());
    }
    
    /*
     * Verifica si esta habilitado
     * */
    @Override
    public boolean isEnabled() {
        return usuario.isActivo();
    }

    @Override
    public @Nullable String getPassword() {
    	return usuario.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // sin roles por ahora
    }
}