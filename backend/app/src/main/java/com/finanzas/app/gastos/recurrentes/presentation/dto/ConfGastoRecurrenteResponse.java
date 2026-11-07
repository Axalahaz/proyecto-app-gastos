package com.finanzas.app.gastos.recurrentes.presentation.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class GastoRecurrenteResponse {
    private Long id;
    private Long categoriaGastoId;
    private Long plantillaId; 
    private String descripcion;
    private Integer diaVencimiento;
    private Integer mesVencimiento;
    private String frecuencia;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCambioActivo;
    
    public static GastoRecurrenteResponse of(
    		Long id,
    		Long categoriaGastoId,
    		Long plantillaId,
    		String descripcion,
    		Integer diaVencimiento,
    		Integer mesVencimiento,
            String frecuencia,
    		boolean activo,
    		LocalDateTime fechaCreacion,
    		LocalDateTime fechaCambioActivo
    		) {
    	GastoRecurrenteResponse response = new GastoRecurrenteResponse();
    	
    	response.id = id;
    	response.categoriaGastoId = categoriaGastoId;
    	response.plantillaId = plantillaId;
    	response.descripcion = descripcion;
    	response.diaVencimiento = diaVencimiento;
    	response.mesVencimiento = mesVencimiento;
    	response.frecuencia = frecuencia;
    	response.activo = activo;
    	response.fechaCreacion = fechaCreacion;
    	response.fechaCambioActivo = fechaCambioActivo;
    	return response;
    }
}