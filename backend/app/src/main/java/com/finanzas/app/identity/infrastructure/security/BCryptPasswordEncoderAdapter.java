package com.finanzas.app.identity.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.finanzas.app.identity.application.PasswordEncoder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Adaptador de infraestructura que implementa el contrato
 * {@link PasswordEncoder} definido por la capa de aplicación.
 *
 * Permite gestionar la codificación y verificación de contraseñas
 * utilizando el algoritmo BCrypt proporcionado por Spring Security,
 * sin que la aplicación dependa directamente de su implementación.
 *
 * De esta manera, la capa de aplicación utiliza una abstracción
 * para trabajar con contraseñas, mientras que la infraestructura
 * se encarga de proporcionar el mecanismo técnico correspondiente.
 *
 * Utiliza Lombok para generar el constructor y el método getter
 * del codificador de Spring Security.
 *
 * @see PasswordEncoder
 * @see BCryptPasswordEncoder
 */
@Getter
@Component
@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter implements PasswordEncoder {

	/**
     * Implementación de BCrypt proporcionada por Spring Security,
     * utilizada para codificar y verificar contraseñas.
     */
    private final BCryptPasswordEncoder springEncoder;

    /**
     * Codifica una contraseña en texto plano utilizando BCrypt.
     *
     * El resultado es un hash que puede almacenarse de forma segura
     * sin conservar la contraseña original.
     *
     * @param rawPassword contraseña original en texto plano.
     * @return hash de la contraseña generado mediante BCrypt.
     */
    @Override
    public String encode(String rawPassword) {
        return springEncoder.encode(rawPassword);
    }

    /**
     * Comprueba si una contraseña en texto plano coincide
     * con un hash previamente generado.
     *
     * Delega la comparación en BCrypt, que realiza la verificación
     * utilizando el mecanismo correspondiente al algoritmo.
     *
     * @param rawPassword contraseña original proporcionada
     *                    durante la autenticación.
     * @param encodedPassword hash de la contraseña almacenado.
     * @return {@code true} si la contraseña coincide con el hash;
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return springEncoder.matches(rawPassword, encodedPassword);
    }
}