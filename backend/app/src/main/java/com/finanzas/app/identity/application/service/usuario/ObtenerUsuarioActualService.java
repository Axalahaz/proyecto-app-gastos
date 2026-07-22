package com.finanzas.app.identity.application.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.identity.presentation.dto.UsuarioResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ObtenerUsuarioActualService {
	
	private final UsuarioRepository usuarioRepository;
    private final UsuarioAutenticado usuarioAutenticado;

    public UsuarioResponse ejecutar() {

        Long userId = usuarioAutenticado.obtenerId();

        Usuario usuario = usuarioRepository.buscarPorId(userId)
                .orElseThrow(() -> NotFoundException.of("Usuario", userId));

        return UsuarioResponse.builder()
        		.id(usuario.getId())
        		.email(usuario.getEmail())
        		.activo(usuario.isActivo())
        		.fechaCreacion(usuario.getFechaCreacion())
        		.fechaModificacion(usuario.getFechaModificacion())
        		.fechaCambioEstado(usuario.getFechaCambioEstado())
        		.fechaCambioPassword(usuario.getFechaCambioPassword())
        		.build();
    }
}
