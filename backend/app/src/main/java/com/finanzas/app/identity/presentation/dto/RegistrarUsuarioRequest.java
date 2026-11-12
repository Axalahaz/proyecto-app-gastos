package com.finanzas.app.identity.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistrarUsuarioRequest (
    
	@NotBlank(message = "El nombre de usuario es obligatorio")
	@Pattern(
			regexp = "^\\S+$",
			message = "El nombre de usuario no debe tener espacios"
			)
	@Size(max = 10, message = "El nombre de usuario debe tener Max 10 caracteres")
	String nombreUsuario,

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "Debe ser un email válido")
	@Pattern(
			regexp = "^\\S+$",
	        message = "El email no debe tener espacios"
	    )
	String email,
    
	@NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 8, message = "La contraseña debe tener entre 4 y 8 caracteres")
    @Pattern(
    		regexp = "^\\S+$",
    		message = "La contraseña no debe tener espacio"
    )
	String password
	
) {}