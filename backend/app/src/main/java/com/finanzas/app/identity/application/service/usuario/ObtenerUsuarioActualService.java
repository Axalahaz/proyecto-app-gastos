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

        return new UsuarioResponse(
        		usuario.getId(),
        		usuario.getNombreUsuario(),
        		usuario.getEmail().value(),
        		usuario.isActivo(),
        		usuario.getFechaCreacion().value(),
        		usuario.getFechaModificacion().value(),
        		usuario.getFechaCambioPassword().value()
        		);
    }
}
