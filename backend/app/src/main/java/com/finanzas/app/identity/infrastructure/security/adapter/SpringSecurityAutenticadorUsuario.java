package com.finanzas.app.identity.infrastructure.security.adapter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.finanzas.app.identity.application.exception.InvalidCredentialsException;
import com.finanzas.app.identity.application.port.out.AutenticadorUsuario;
import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.shared.exception.exceptionImpl.NoAutenticadoException;

import lombok.RequiredArgsConstructor;

/**
 * Adaptador de infraestructura que implementa el puerto {@link AutenticadorUsuario}
 * utilizando el mecanismo de autenticación de Spring Security.
 *
 * Delega la validación de las credenciales en {@link AuthenticationManager}
 * y devuelve la entidad {@link Usuario} correspondiente cuando la autenticación
 * es exitosa.
 *
 * De esta manera, la capa de aplicación puede solicitar la autenticación
 * sin depender directamente de Spring Security.
 *
 * @see AutenticadorUsuario
 * @see AuthenticationManager
 */
@Component
@RequiredArgsConstructor
public class SpringSecurityAutenticadorUsuario implements AutenticadorUsuario {

	/**
     * Administrador de autenticación configurado en {@code SecurityConfig}.
     *
     * En esta aplicación, Spring inyecta una instancia de {@code ProviderManager},
     * que delega la autenticación en el proveedor correspondiente.
     */
    private final AuthenticationManager authenticationManager;

    @Override
    public Usuario autenticar(String email, String password) {
    	
    	Authentication authentication;

    	/*
    	 * UsernamePasswordAuthenticationToken(email, password):
    	 *
    	 * Crea un objeto que implementa Authentication y transporta
    	 * las credenciales que se desean autenticar.
    	 *
    	 * - principal: email.
    	 * - credentials: contraseña ingresada.
    	 * - authenticated: false, porque todavía no fue autenticado.
    	 * 
    	 * FLUJO INTERNO DE AUTENTICACIÓN:
    	 *
    	 * 1. ProviderManager busca en su lista un proveedor que soporte
    	 *    el tipo de Authentication recibido.
    	 *
    	 * 2. DaoAuthenticationProvider recibe la solicitud y ejecuta
    	 *    su lógica interna de autenticación.
    	 *
    	 * 3. UserDetailsServiceImpl ejecuta loadUserByUsername(email)
    	 *    para buscar al usuario.
    	 *
    	 * 4. UsuarioRepository consulta la base de datos y devuelve
    	 *    la entidad Usuario.
    	 *
    	 * 5. UserDetailsAdapter adapta la entidad Usuario al contrato
    	 *    UserDetails que necesita Spring Security.
    	 *
    	 * 6. DaoAuthenticationProvider utiliza el PasswordEncoder
    	 *    configurado previamente para ejecutar matches().
    	 *
    	 * 7. Si las credenciales son correctas, devuelve un Authentication
    	 *    autenticado. Si no, lanza una excepción de autenticación.
    	 */
    	try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

    	/*
         * Si la autenticación fue exitosa, se obtiene el principal
         * devuelto por Spring Security.
         *
         * En nuestra implementación, el principal es UserDetailsAdapter,
         * que permite recuperar la entidad Usuario original.
         */
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsAdapter userDetails) {
            return userDetails.getUsuario();
        } 

        throw new NoAutenticadoException("No se pudo identificar al usuario autenticado");
    }
}
