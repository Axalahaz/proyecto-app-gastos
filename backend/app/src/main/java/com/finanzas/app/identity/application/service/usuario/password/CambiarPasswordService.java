package com.finanzas.app.identity.application.service.usuario.password;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.PasswordEncoder;
import com.finanzas.app.identity.application.exception.InvalidCredentialsException;
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
public class CambiarPasswordService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final PasswordEncoder passwordEncoder;

    public void ejecutar(String passwordActual, String nuevaPassword) {

        Long userId = usuarioAutenticado.obtenerId();

        Usuario usuario = usuarioRepository.buscarPorId(userId)
                .orElseThrow(() -> NotFoundException.of("Usuario", userId));

        // valido password actual contra BD
        if (!passwordEncoder.matches(passwordActual, usuario.getPasswordHash())) {
            throw new InvalidCredentialsException("Credenciales invalidas");
        }

        // valido nueva contraseña
        if (!passwordEncoder.matches(usuario.getPasswordHash(), nuevaPassword)) {
            throw new ConflictException("La nueva contraseña debe ser diferente a la actual");
        }
        
        // encripto nueva password
        String nuevaPasswordEncriptada = passwordEncoder.encode(nuevaPassword);

        usuario.cambiarPassword(
                nuevaPasswordEncriptada,
                new Fecha(LocalDateTime.now())
        );

        usuarioRepository.guardar(usuario);
    }
}