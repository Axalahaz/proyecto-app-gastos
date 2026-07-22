package com.finanzas.app.identity.domain;

/*
 * interfaz de encriptación
 * 
 * permite cambiar implementación (BCrypt hoy, otra mañana)
 * 
 * */
public interface PasswordEncoder {

	String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
