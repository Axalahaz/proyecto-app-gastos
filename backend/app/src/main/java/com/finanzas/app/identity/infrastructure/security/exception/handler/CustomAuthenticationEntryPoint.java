package com.finanzas.app.identity.infrastructure.security.exception.handler;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finanzas.app.shared.exception.model.ErrorResponse;
import com.finanzas.app.shared.exception.model.ErrorType;
import com.finanzas.app.identity.infrastructure.security.exception.SecurityErrorMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * AuthenticationEntryPoint: el usuario no está autenticado correctamente.
 * Encargado de responder cuando da error el usuario.
 *  401
 * */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;
	private final SecurityErrorMapper securityErrorMapper;
	
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        
        String causeMessage = authException.getCause() != null
		        ? authException.getCause().getMessage()
		        : (authException.getMessage() != null ? authException.getMessage() : "Error inesperado");
        
        ErrorType errorType = securityErrorMapper.toErrorType(authException);
        
        ErrorResponse error = ErrorResponse.crear(
        		errorType.getStatus().value(),
        		errorType,
        	    "Error de autenticación", 
        	    List.of(causeMessage) // cambiar para produccion: "No tiene permisos para realizar esta operación"
        	);
        
        response.setStatus(errorType.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
        		objectMapper.writeValueAsString(error)
        		);
    }
}