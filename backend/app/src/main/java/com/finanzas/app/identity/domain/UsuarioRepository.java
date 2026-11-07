package com.finanzas.app.identity.domain;

import java.util.Optional;

public interface UsuarioRepository {

	Usuario guardar(Usuario usuario);

	int eliminar(Long id);
    
    Optional<Usuario> buscarPorEmail(String email);
    
    Optional<Usuario> buscarPorId(Long id);
}