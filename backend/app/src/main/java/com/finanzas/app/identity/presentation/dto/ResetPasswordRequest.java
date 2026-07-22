package com.finanzas.app.identity.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ResetPasswordRequest {
	
	@NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 8, message = "La contraseña debe tener entre 4 y 8 caracteres")
    @Pattern(
    		regexp = "^\\S+$",
        message = "La contraseña no debe tener espacio"
    )
	private String password;
	
	@NotBlank(message = "El token es obligatorio")
    @Pattern(
    		regexp = "^\\S+$",
        message = "El token no debe tener espacio"
    )
	private String token;
    
}