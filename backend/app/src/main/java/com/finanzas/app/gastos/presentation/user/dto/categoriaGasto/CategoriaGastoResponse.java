package com.finanzas.app.gastos.presentation.user.dto.categoriaGasto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoriaGastoResponse {
    private Long id;
    private String nombre;
    
    // ----------------------------------
    // OF
    
    public static CategoriaGastoResponse of(
    		Long id,
    		String nombre
    		) {
    	return new CategoriaGastoResponse(id, nombre);
    }
}