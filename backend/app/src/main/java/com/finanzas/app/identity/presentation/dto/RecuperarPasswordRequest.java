package com.finanzas.app.identity.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RecuperarPasswordRequest (
	
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "Debe ser un email válido")
	@Pattern(
			regexp = "^\\S+$",
	        message = "El email no debe tener espacios"
	    )
    String email
    
) {}