package com.finanzas.app.shared.infrastructure.security.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenInvalidoException extends AuthenticationException{

	public TokenInvalidoException(String message) {
		super(message);
	}
}
