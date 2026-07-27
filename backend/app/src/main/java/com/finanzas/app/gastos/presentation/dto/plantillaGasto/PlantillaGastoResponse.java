package com.finanzas.app.gastos.presentation.dto.plantillaGasto;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.model.TipoObjeto;

import lombok.Getter;

@Getter
public class PlantillaGastoResponse {
	private Long id;
	private Long categoriaGastoId;
    private String descripcion;
    private TipoObjeto tipo;
    private LocalDateTime fechaCreacion;
	
    public static PlantillaGastoResponse of(
    		Long id,
    		Long categoriaGastoId,
    		String descripcion,
    		TipoObjeto tipo,
    		LocalDateTime fechaCreacion
    		) {
    	PlantillaGastoResponse response = new PlantillaGastoResponse();
    	
    	response.id = id;
    	response.categoriaGastoId = categoriaGastoId;
    	response.descripcion = descripcion;
    	response.tipo = tipo;
    	response.fechaCreacion = fechaCreacion;
    	return response;
    }
    
}