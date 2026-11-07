package com.finanzas.app.identity.infrastructure.security.exception;

import org.springframework.security.core.AuthenticationException;

// El usuario no puede ser considerado autenticado

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(String msg) {
        super(msg);
    }
}