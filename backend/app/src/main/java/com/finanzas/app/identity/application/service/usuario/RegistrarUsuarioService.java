package com.finanzas.app.identity.application.service.usuario;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.PasswordEncoder;
import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class RegistrarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario ejecutar(
    		String nombreUsuario, 
    		String email, 
    		String password
    	) {

        usuarioRepository.buscarPorEmail(email)
            .ifPresent(u -> {
                throw new ConflictException("El email ya está registrado");
            });

        Fecha fechaRegistro = new Fecha(LocalDateTime.now());
        String passwordEncriptada = passwordEncoder.encode(password);
        
        Usuario usuario = Usuario.crear(
        		nombreUsuario, 
        		email, 
        		passwordEncriptada, 
        		fechaRegistro
        );
        
        Usuario guardado = usuarioRepository.guardar(usuario);
        
        return guardado;
    }
}