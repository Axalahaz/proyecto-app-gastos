package com.finanzas.app.identity.application.service.usuario.password;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.identity.infrastructure.security.jwt.JwtTokenService;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class SolicitarRecuperacionPasswordService {

	private final UsuarioRepository usuarioRepository;
	private final JwtTokenService jwtTokenService;

    public String ejecutar(String email) {

        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> NotFoundException.of("Usuario no encontrado"));

        usuario.verificarEstadoActivo();
        
        // este token no pasa por el jwtuAtheticationFilter
        return jwtTokenService.generarTokenRecuperacion(usuario);
    }
}
