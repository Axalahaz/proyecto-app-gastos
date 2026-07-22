package com.finanzas.app.identity.application.service.usuario;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.exception.EmailYaRegistradoException;
import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ModificarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(String nuevoEmail) {

        Long userId = usuarioAutenticado.obtenerId();

        Usuario usuario = usuarioRepository.buscarPorId(userId)
                .orElseThrow(() -> NotFoundException.of("Usuario", userId));

        // validar email único (si cambia)
        if (nuevoEmail != null && !usuario.getEmail().equals(nuevoEmail)) {
            usuarioRepository.buscarPorEmail(nuevoEmail)
                    .ifPresent(u -> {
                        throw new EmailYaRegistradoException();
                    });
        }

        usuario.actualizarDatos(
                nuevoEmail,
                new Fecha(LocalDateTime.now())
        );

        usuarioRepository.guardar(usuario);
    }
}