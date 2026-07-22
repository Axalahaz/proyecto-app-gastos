package com.finanzas.app.identity.application.service.usuario.password;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.exception.ContraseñaDuplicadaException;
import com.finanzas.app.identity.application.exception.InvalidCredentialsException;
import com.finanzas.app.identity.domain.PasswordEncoder;
import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.NotFoundException;

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
        if (!passwordEncoder.matches(passwordActual, usuario.getPassword())) {
            throw new InvalidCredentialsException("Credenciales invalidas");
        }

        // valido nueva contraseña
        if (passwordActual.equals(nuevaPassword)) {
            throw new ContraseñaDuplicadaException();
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