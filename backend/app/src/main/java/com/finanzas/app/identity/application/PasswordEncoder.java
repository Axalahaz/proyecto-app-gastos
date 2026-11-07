package com.finanzas.app.identity.application;

/*
 * interfaz de encriptación.
 * permite cambiar implementación (BCrypt hoy, otra mañana)
 * 
 * */
public interface PasswordEncoder {

	// Recibe la contraseña original y devuelve su hash.
	String encode(String rawPassword);

	// Comprueba si la contraseña ingresada corresponde al hash almacenado.
    boolean matches(String rawPassword, String encodedPassword);
}