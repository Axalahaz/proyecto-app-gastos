package com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class PlantillaGastoRecurrenteResponse {
    private Long id;
    private String descripcion;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    
    public static PlantillaGastoRecurrenteResponse of(
    		Long id,
    		String descripcion,
    		boolean activo,
    		LocalDateTime fechaCreacion
    		) {
    	PlantillaGastoRecurrenteResponse response = new PlantillaGastoRecurrenteResponse();
    	
    	response.id = id;
    	response.descripcion = descripcion;
    	response.activo = activo;
    	response.fechaCreacion = fechaCreacion;
    	return response;
    }
}