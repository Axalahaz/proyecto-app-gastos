package com.finanzas.app.identity.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CambiarPasswordRequest (
	
	@NotBlank(message = "La contraseña actual es obligatoria")
    @Size(min = 4, max = 8, message = "La contraseña actual debe tener entre 4 y 8 caracteres")
    @Pattern(
    		regexp = "^\\S+$",
    		message = "La contraseña actual no debe tener espacio"
    )
	String passwordActual,
	
	@NotBlank(message = "La contraseña nueva es obligatoria")
    @Size(min = 4, max = 8, message = "La contraseña nueva debe tener entre 4 y 8 caracteres")
    @Pattern(
    		regexp = "^\\S+$",
    		message = "La contraseña nueva no debe tener espacio"
    )
	String nuevaPassword
) {}