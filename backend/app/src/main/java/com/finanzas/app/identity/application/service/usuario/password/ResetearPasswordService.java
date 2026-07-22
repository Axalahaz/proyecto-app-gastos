package com.finanzas.app.identity.application.service.usuario.password;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.exception.InvalidCredentialsException;
import com.finanzas.app.identity.application.exception.ResetPasswordInactivaException;
import com.finanzas.app.identity.domain.PasswordEncoder;
import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.NotFoundException;
import com.finanzas.app.shared.infrastructure.security.jwt.JwtTokenService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ResetearPasswordService {
	
	private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    

    public void ejecutar(String nuevoPassword, String tokenReset) {
    	
    	Claims claims;
		try {
			claims = jwtTokenService.obtenerClaimsTokenRecuperacionSeguro(tokenReset);
		} catch (Exception e) {
			throw new InvalidCredentialsException("Token inválido para recuperación");
		}

		String userId = claims.getSubject();
		Integer versionToken = claims.get("resetVersion", Integer.class);

    	Usuario usuario = usuarioRepository.buscarPorId(Long.valueOf(userId))
    	        .orElseThrow(() -> NotFoundException.of("Usuario", userId));
    	
        // evita la reutilizacion del token si ya se cambio la contraseña
        // cada cambio de contraseña aumenta un contador la de version
    	if (!usuario.getResetPasswordVersion().equals(versionToken)) {
    	    throw new ResetPasswordInactivaException();
    	}
    	
        String passwordSegura = passwordEncoder.encode(nuevoPassword); // encriptacion
        
        usuario.cambiarPassword(passwordSegura, new Fecha(LocalDateTime.now()));
        usuario.incrementarResetPasswordVersion();
        
        usuarioRepository.guardar(usuario);
    }

}
