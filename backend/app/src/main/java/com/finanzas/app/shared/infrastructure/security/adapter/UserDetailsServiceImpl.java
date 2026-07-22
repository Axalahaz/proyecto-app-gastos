package com.finanzas.app.shared.infrastructure.security.adapter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

// NO ES UN CASO DE USO
// es el service del adaptador de infraestructura
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String id) {

        Usuario usuario = usuarioRepository.buscarPorId(Long.valueOf(id))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new UserDetailsAdapter(usuario);
    }
}