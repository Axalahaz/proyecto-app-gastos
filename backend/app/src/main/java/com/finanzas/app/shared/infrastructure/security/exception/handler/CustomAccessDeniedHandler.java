package com.finanzas.app.shared.infrastructure.security.exception.handler;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finanzas.app.shared.dto.ErrorResponse;
import com.finanzas.app.shared.error.ErrorType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * Da error cuando falla la autorizacion de Usuario (se usa para roles y en este momento no se tiene)
 * 403
 * */
@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private final ObjectMapper objectMapper;
	
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {

        String causeMessage = accessDeniedException.getCause() != null
		        ? accessDeniedException.getCause().getMessage()
		        : (accessDeniedException.getMessage() != null ? accessDeniedException.getMessage() : "Error inesperado");
        
        ErrorType errorType = ErrorType.FORBIDDEN;
        
        ErrorResponse error = ErrorResponse.crear(
        		errorType.getStatus().value(),
        	    ErrorType.FORBIDDEN,
        	    "Usuario sin permisos",
        	    List.of(causeMessage) // cambiar para produccion
        	);
        
        response.setStatus(errorType.getStatus().value());
        response.setContentType("application/json");

        response.getWriter().write(
        		objectMapper.writeValueAsString(error));
    }
}