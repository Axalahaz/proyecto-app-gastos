package com.finanzas.app.identity.application.service.usuario.password;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;
import com.finanzas.app.shared.infrastructure.security.jwt.JwtTokenService;

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

        usuario.verificarActivo();
        
        // este token no pasa por el jwtuAtheticationFilter
        return jwtTokenService.generarTokenRecuperacion(usuario);
    }
}
