package com.finanzas.app.identity.application.service.usuario;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.PasswordEncoder;
import com.finanzas.app.identity.domain.Email;
import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

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
            .ifPresent(_ -> {
                throw new ConflictException("El email ya está registrado");
            });

        if (password.length() > 8 || password.length() < 4) {
            throw ValidationException.of("La contraseña debe tener entre 4 y 8 caracteres");
        }
        
        Fecha fechaRegistro = new Fecha(LocalDateTime.now());
        String passwordEncriptada = passwordEncoder.encode(password);
        Email emailVO = new Email(email);
        
        Usuario usuario = Usuario.crear(
        		nombreUsuario, 
        		emailVO, 
        		passwordEncriptada, 
        		fechaRegistro
        );
        
        Usuario guardado = usuarioRepository.guardar(usuario);
        
        return guardado;
    }
}