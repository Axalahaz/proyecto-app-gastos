package com.finanzas.app.identity.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ModificarUsuarioRequest (
	
	@Pattern(
			regexp = "^\\S+$",
	        message = "El email no debe tener espacios"
	    )
	@Email(message = "Debe ser un email válido")
	String email,
	
	@Pattern(
			regexp = "^\\S+$",
			message = "El nombre de usuario no debe tener espacios"
			)
	@Size(max = 10, message = "El nombre de usuario debe tener Max 10 caracteres")
	String nombreUsuario
) {}
