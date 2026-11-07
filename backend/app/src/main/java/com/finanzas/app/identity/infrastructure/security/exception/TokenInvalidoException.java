package com.finanzas.app.identity.infrastructure.security.exception;

import org.springframework.security.core.AuthenticationException;

//El usuario no puede ser considerado autenticado

public class TokenInvalidoException extends AuthenticationException{

	public TokenInvalidoException(String message) {
		super(message);
	}
}
