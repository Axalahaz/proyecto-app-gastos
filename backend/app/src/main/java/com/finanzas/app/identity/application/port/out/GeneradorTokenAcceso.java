package com.finanzas.app.identity.application.port.out;

import com.finanzas.app.identity.domain.Usuario;

/**
 * Puerto de salida que define el contrato para la generación
 * de tokens de acceso.
 *
 * Permite que la capa de aplicación solicite la generación
 * de un token asociado a un usuario sin depender de una
 * tecnología o mecanismo de implementación específico.
 *
 * Las implementaciones concretas son responsabilidad de la
 * capa de infraestructura.
 *
 * @see Usuario
 */
public interface GeneradorTokenAcceso {
	
	/**
     * Genera un token de acceso asociado al usuario indicado.
     *
     * La implementación determina el mecanismo técnico utilizado
     * para generar y firmar el token.
     *
     * @param usuario usuario autenticado al que se asociará el token.
     * @return token de acceso generado.
     */
    String generarToken(Usuario usuario);
}