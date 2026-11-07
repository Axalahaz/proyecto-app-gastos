package com.finanzas.app.identity.application.port.out;

import com.finanzas.app.identity.domain.Usuario;

/**
 * Puerto de salida que define el contrato para la autenticación
 * de usuarios mediante sus credenciales.
 *
 * Permite que los casos de uso de la capa de aplicación soliciten
 * la validación de las credenciales y la recuperación del usuario
 * autenticado sin depender de una tecnología o mecanismo específico.
 *
 * Las implementaciones concretas son responsabilidad de la
 * capa de infraestructura.
 *
 * @see Usuario
 */
public interface AutenticadorUsuario {

	/**
     * Autentica a un usuario mediante su correo electrónico
     * y contraseña.
     *
     * Si las credenciales son válidas, devuelve el usuario
     * correspondiente. En caso contrario, la implementación
     * comunica el error mediante la excepción correspondiente.
     *
     * @param email dirección de correo electrónico del usuario.
     * @param password contraseña proporcionada para la autenticación.
     * @return usuario autenticado.
     */
    Usuario autenticar(String email, String password);
}