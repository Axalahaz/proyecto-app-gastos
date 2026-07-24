package com.finanzas.app.gastos.presentation.dto.gastoRecurrente;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class GastoRecurrenteResponse {
    private Long id;
    private String descripcion;
    private Integer diaVencimiento;
    private Integer mesVencimiento;
    private String frecuencia;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCambioActivo;
    
    public static GastoRecurrenteResponse of(
    		Long id,
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