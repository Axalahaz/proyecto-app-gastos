package com.finanzas.app.gastos.presentation.dto.gasto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class GastoResponse {
    private Long id;
    private Long categoriaGastoId;
    private BigDecimal monto;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaVencimiento;
    private LocalDateTime fechaCambioEstado;
    
    public static GastoResponse of(
    		Long id,
    		Long categoriaGastoId,
    		BigDecimal monto,
    		String descripcion,
    		String estado,
    		LocalDateTime fechaCreacion,
    		LocalDateTime fechaVencimiento,
    		LocalDateTime fechaCambioEstado
    		) {
    	GastoResponse response = new GastoResponse();
    	
    	response.id = id;
    	response.categoriaGastoId = categoriaGastoId;
    	response.monto = monto;
    	response.descripcion = descripcion;
    	response.estado = estado;
    	response.fechaCreacion = fechaCreacion;
    	response.fechaVencimiento = fechaVencimiento;
    	response.fechaCambioEstado = fechaCambioEstado;
    	return response;
    }
}