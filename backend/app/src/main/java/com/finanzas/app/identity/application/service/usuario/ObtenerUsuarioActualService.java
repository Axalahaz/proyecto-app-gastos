package com.finanzas.app.identity.application.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.identity.presentation.dto.UsuarioResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ObtenerUsuarioActualService {
	
	private final UsuarioRepository usuarioRepository;
    private final UsuarioAutenticado usuarioAutenticado;

    public UsuarioResponse ejecutar() {

        Long usuarioId = usuarioAutenticado.obtenerId();

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> NotFoundException.of("Usuario", usuarioId));

        return UsuarioResponse.builder()
        		.id(usuario.getId())
        		.nombreUsuario(usuario.getNombreUsuario())
        		.email(usuario.getEmail())
        		.activo(usuario.isActivo())
        		.fechaCreacion(usuario.getFechaCreacion().value())
        		.fechaModificacion(usuario.getFechaModificacion().value())
        		.fechaCambioPassword(usuario.getFechaCambioPassword().value())
        		.build();
    }
}
