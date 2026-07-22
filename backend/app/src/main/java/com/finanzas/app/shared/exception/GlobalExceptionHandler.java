package com.finanzas.app.shared.exception;

import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.finanzas.app.shared.dto.ErrorResponse;
import com.finanzas.app.shared.error.ErrorType;
import com.finanzas.app.shared.exception.extend.TooManyRequestsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * Logica centralizada de entidadException!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * */
	@ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(
            DomainException ex) {

		return buildResponse(
				ex.getError(), // errortype
				ex.getMessage(), 
				ex.getDetails()
				);
    }
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@ExceptionHandler(TooManyRequestsException.class)
	public ResponseEntity<ErrorResponse> handleTooManyRequests(TooManyRequestsException ex) {

	    ErrorType error = ErrorType.TOO_MANY_REQUESTS;

	    ErrorResponse response = ErrorResponse.crear(
	    		error.getStatus().value(),
	            error,
	            ex.getMessage(),
	            ex.getDetails()
	    );

	    return ResponseEntity
	            .status(error.getStatus())
	            .header("Retry-After", "60") // segundos - tiempo de refresco automatico
	            .body(response);
	}
	
	/*
	 * Salta cuando el cliente usa un metodo HTTP no permitido
	 * ej:
	 * GET /usuarios  -> (endpoint solo acepta POST)
	 * */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {

	    List<String> errores = List.of(ex.getMethod() + " no está soportado para este endpoint");

	    return buildResponse(
	    		ErrorType.METHOD_NOT_ALLOWED, 
	    		"Método HTTP no permitido",  
    			errores
				);
	}
	
	/*
	 * Se ejecuta cuando no se puede convertir al objeto esperado
	 * ej:
	 * se recibe JSON y hay que convertirlo a objetoRequest y falla.
	 * cuando falla el envio de un enum
	 * */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleInvalidBody(
	        HttpMessageNotReadableException ex) {

	    return buildResponse(
	            ErrorType.INVALID_BODY,
	            "Error de formato en el cuerpo de la solicitud",
	            List.of("El JSON enviado es inválido")
	    );
	}
	
    /**
     * Errores de validación del DTO (@Valid + @RequestBody)
     * ej: 
     * public void crear(@Valid @RequestBody UsuarioRequest req)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

    	List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
    	
    	return buildResponse(
    			ErrorType.INVALID_BODY, 
    			"Error de validación en el cuerpo de la solicitud",  
    			errores
				);
    }
    
    /**
     * Errores de validación de tipo de parámetro en el endpoint 
     * ej:
     * @GetMapping("/{id}")
     * public void get(@PathVariable Long id)
     * GET /usuarios/abc -> "abc" no es Long!
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

    	String tipo = ex.getRequiredType() != null
    	        ? ex.getRequiredType().getSimpleName()
    	        : "desconocido";
    	
        String mensaje = String.format(
            "El parámetro '%s' debe ser de tipo %s",
            ex.getName(),
            tipo
        		);
        
    	return buildResponse(
    			ErrorType.INVALID_REQUEST, 
    			"Tipo de parámetro inválido",  
    			List.of(mensaje)
				);
    }
    
    /**
     * Errores de validación en parámetros (@RequestParam, @PathVariable, @RequestHeader)
     * NO body
     * ej:
     * @GetMapping("/buscar")
     * public List<Usuario> buscar(@RequestParam @NotBlank String nombre,@RequestParam @Min(18) int edad)
     * GET /buscar?nombre=&edad=10 -> nombre vacio y edad menor a 18
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {

        List<String> errores = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());
        
    	return buildResponse(
    			ErrorType.INVALID_REQUEST, 
    			"Error de validación en parámetros de la solicitud",   
    			errores
				);
    }

    /**
     * Fallback para errores no controlados
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {

    	List<String> errores = ex.getMessage() != null
    	        ? List.of(ex.getMessage())
    	        : List.of();
    	
    	return buildResponse(
    			ErrorType.INTERNAL_SERVER_ERROR, 
    			"Error interno del servidor", 
    			errores
				);
    }
    
    // ----------------------------------------------
    // METODO PRIVADO
    
    private ResponseEntity<ErrorResponse> buildResponse(
    		ErrorType error,
    		String message,
    		List<String> details
    		) {
        return ResponseEntity
            .status(error.getStatus())
            .body(
            		ErrorResponse.crear(
		                error.getStatus().value(),
		                error,
		                message,
		                details
		            )
            );
    }
    
}