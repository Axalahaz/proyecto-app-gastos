package com.finanzas.app.gastos.application.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.presentation.user.dto.gastoRecurrente.GastoRecurrenteResponse;

@Component
public class GastoRecurrenteApplicationMapper {
	
    public GastoRecurrenteResponse mapToResponse(GastoRecurrente gastoRecurrente) {
    	LocalDateTime fechaCambioActivo = gastoRecurrente.getFechaCambioActivo() != null
                ? gastoRecurrente.getFechaCambioActivo().getValue()
                : null;
    	
    	return GastoRecurrenteResponse.of(
    			gastoRecurrente.getId(), 
    			gastoRecurrente.getDescripcion(),
    			gastoRecurrente.getCategoriaGastoId(),
        		gastoRecurrente.getPeriodicidad().getDiaVencimiento(),
        		gastoRecurrente.getPeriodicidad().getMesVencimiento(),
        		gastoRecurrente.getPeriodicidad().getFrecuencia().toString(),
        		gastoRecurrente.isActivo(),
        		gastoRecurrente.getFechaCreacion().getValue(), 
        		fechaCambioActivo
        		);
    }
}
