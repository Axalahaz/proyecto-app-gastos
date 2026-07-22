package com.finanzas.app.identity.application.service.usuario;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.exception.InvalidCredentialsException;
import com.finanzas.app.identity.domain.PasswordEncoder;
import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * No se utiliza usuarioAutenticado porque no se usa jwtfilter para 
 * reactivar la cuenta
 * */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ReactivarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void ejecutar(String email, String password) {

        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));
        
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
        	log.warn("Intento de reactivación fallido para usuario {}", usuario.getId());
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        usuario.reactivar(new Fecha(LocalDateTime.now()));

        usuarioRepository.guardar(usuario);
        
        log.info("Usuario {} reactivado correctamente", usuario.getId());
    }
}