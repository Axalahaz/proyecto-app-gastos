package com.finanzas.app.shared.infrastructure.security.exception.handler;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finanzas.app.shared.dto.ErrorResponse;
import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.infrastructure.security.SecurityErrorMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * Encargado de responder cuando da error un usuario que deberia estar autenticado
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
        	    List.of(causeMessage) // cambiar para produccion
        	);
        
        response.setStatus(errorType.getStatus().value());
        response.setContentType("application/json");

        response.getWriter().write(
        		objectMapper.writeValueAsString(error)
        		);
    }
}