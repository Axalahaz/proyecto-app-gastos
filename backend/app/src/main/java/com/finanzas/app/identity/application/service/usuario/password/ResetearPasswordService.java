package com.finanzas.app.identity.application.service.usuario.password;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.PasswordEncoder;
import com.finanzas.app.identity.application.port.out.token.TokenRecuperacionData;
import com.finanzas.app.identity.application.port.out.token.ValidadorTokenRecuperacion;
import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.ForbiddenException;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ResetearPasswordService {
	
	private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidadorTokenRecuperacion validadorTokenRecuperacion;
    

    public void ejecutar(String nuevoPassword, String tokenReset) {
    	
    	TokenRecuperacionData datos = validadorTokenRecuperacion.validarTokenRecuperacionSeguro(tokenReset);

    	Usuario usuario = usuarioRepository.buscarPorId(datos.usuarioId())
    	        .orElseThrow(() -> NotFoundException.of("Usuario", datos.usuarioId()));
    	
        // evita la reutilizacion del token si ya se cambio la contraseña
        // cada cambio de contraseña aumenta un contador la de version
    	if (!usuario.getResetPasswordVersion().equals(datos.resetVersion())) {
    	    throw new ForbiddenException("Codigo de reseteo de contraseña usado.");
    	}
    	
    	if (passwordEncoder.matches(nuevoPassword, usuario.getPasswordHash())) {
    		throw new ConflictException("Elegir una contraseña diferente a la última");
    	}

    	String nuevaPasswordSegura = passwordEncoder.encode(nuevoPassword); // encriptacion
        
        usuario.cambiarPassword(nuevaPasswordSegura, new Fecha(LocalDateTime.now()));
        
        usuarioRepository.guardar(usuario);
    }

}
