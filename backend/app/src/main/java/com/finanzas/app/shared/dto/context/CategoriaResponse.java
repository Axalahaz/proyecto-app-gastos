package com.finanzas.app.shared.dto.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoriaResponse {
    private Long id;
    private String nombre;
    
    // ----------------------------------
    // OF
    
    public static CategoriaResponse of(
    		Long id,
    		String nombre
    		) {
    	return new CategoriaResponse(id, nombre);
    }
}