package com.finanzas.app.identity.application.service.usuario;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ModificarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(String nuevoNombreUsuario, String nuevoEmail) {

        Long userId = usuarioAutenticado.obtenerId();

        Usuario usuario = usuarioRepository.buscarPorId(userId)
                .orElseThrow(() -> NotFoundException.of("Usuario", userId));

        // validar email único (si cambia)
        if (nuevoEmail != null && !usuario.getEmail().equals(nuevoEmail)) {
            usuarioRepository.buscarPorEmail(nuevoEmail)
                    .ifPresent(u -> {
                        throw new ConflictException("El email ya está registrado");
                    });
        }

        usuario.actualizarEmail(
                nuevoEmail,
                new Fecha(LocalDateTime.now())
        );
        usuario.actualizarNombreUsuario(
        		nuevoNombreUsuario,
                new Fecha(LocalDateTime.now())
        );

        usuarioRepository.guardar(usuario);
    }
}