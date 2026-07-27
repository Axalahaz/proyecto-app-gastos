package com.finanzas.app.gastos.presentation.dto.categoriaGasto;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.model.TipoObjeto;

import lombok.Getter;

@Getter
public class CategoriaGastoResponse {
	private Long id;
    private String nombre;
    private TipoObjeto tipo;
    private LocalDateTime fechaCreacion;
	
    public static CategoriaGastoResponse of(
    		Long id,
    		String nombre,
    		TipoObjeto tipo,
    		LocalDateTime fechaCreacion
    		) {
    	CategoriaGastoResponse response = new CategoriaGastoResponse();
    	
    	response.id = id;
    	response.nombre = nombre;
    	response.tipo = tipo;
    	response.fechaCreacion = fechaCreacion;
    	return response;
    }
    
}