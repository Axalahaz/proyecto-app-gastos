package com.finanzas.app.identity.infrastructure.security.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.finanzas.app.shared.exception.model.ErrorType;

@Component
public class SecurityErrorMapper {

    public ErrorType toErrorType(AuthenticationException ex) {
        
    	if (ex instanceof TokenInvalidoException ||
            ex instanceof TokenExpiredException) {
            return ErrorType.NOT_AUTHENTICATED;
        }

    	if (ex instanceof DisabledException) {
            return ErrorType.FORBIDDEN;
        }
        
        if (ex instanceof BadCredentialsException) {
            return ErrorType.INVALID_CREDENTIALS;
        }

        return ErrorType.NOT_AUTHENTICATED;
    }
}