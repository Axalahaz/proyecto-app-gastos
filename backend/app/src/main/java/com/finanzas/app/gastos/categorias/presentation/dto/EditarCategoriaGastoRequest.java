package com.finanzas.app.gastos.categorias.presentation.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EditarCategoriaGastoRequest {

	@Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;
	
}