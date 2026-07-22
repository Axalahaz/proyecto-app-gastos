package com.finanzas.app.identity.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ModificarUsuarioRequest {
	
	@Pattern(
			regexp = "^\\S+$",
	        message = "El email no debe tener espacios"
	    )
	@Email(message = "Debe ser un email válido")
	private String email;
    
}
