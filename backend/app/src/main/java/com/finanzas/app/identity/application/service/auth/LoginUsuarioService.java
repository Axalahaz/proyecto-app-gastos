package com.finanzas.app.identity.application.service.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.port.out.AutenticadorUsuario;
import com.finanzas.app.identity.application.port.out.token.GeneradorTokenAcceso;
import com.finanzas.app.identity.domain.Usuario;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Caso de uso encargado de iniciar el proceso de autenticación de un usuario.
 *
 * Coordina la validación de las credenciales y la generación del token
 * de acceso correspondiente.
 *
 * Delega la autenticación en el puerto de salida
 * {@link AutenticadorUsuario} y, una vez validada la identidad,
 * solicita la generación del token mediante
 * {@link GeneradorTokenAcceso}.
 *
 * No depende de mecanismos tecnológicos específicos para realizar
 * estas operaciones, ya que interactúa con ellos a través de sus contratos.
 *
 * @see AutenticadorUsuario
 * @see GeneradorTokenAcceso
 * @see Usuario
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LoginUsuarioService {
	
	private final AutenticadorUsuario autenticadorUsuario;
	private final GeneradorTokenAcceso generadorTokenAcceso;

    public String ejecutar(String email, String password) {

    	log.debug("Iniciando proceso de autenticación");
    	
        Usuario usuario = autenticadorUsuario.autenticar(
                email,
                password
        );

        String token = generadorTokenAcceso.generarToken(usuario);

        log.info("Autenticación exitosa para el usuario ID: {}", usuario.getId());

        return token;
    }
}
