package com.finanzas.app.shared.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.finanzas.app.shared.error.ErrorType;

@Getter
public class ErrorResponse {
	
	private int status;
    private ErrorType error;
    private String message;
    /**
     * Detalle de errores (opcional)
     * Si esta vacio no se incluye en el JSON
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> details;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    // ------------------------------------------
    // CONSTRUCTOR
    
    private ErrorResponse(
            int status,
            ErrorType error,
            String message,
            List<String> details
    ) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    // ------------------------------------------
    // CREAR

    public static ErrorResponse crear(
            int status,
            ErrorType error,
            String message,
            List<String> details
    ) {
    	List<String> detailsFinal = (details == null || details.isEmpty())
    		    ? List.of()
    		    : List.copyOf(details);
    	
        return new ErrorResponse(status, error, message, detailsFinal);
    }
}