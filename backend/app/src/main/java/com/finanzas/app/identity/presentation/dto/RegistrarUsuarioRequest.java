package com.finanzas.app.identity.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrarUsuarioRequest {
    
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "Debe ser un email válido")
	@Pattern(
			regexp = "^\\S+$",
	        message = "El email no debe tener espacios"
	    )
	private String email;
    
	@NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 8, message = "La contraseña debe tener entre 4 y 8 caracteres")
    @Pattern(
    		regexp = "^\\S+$",
    		message = "La contraseña no debe tener espacio"
    )
	private String password;
	
}