package com.finanzas.app.gastos.recurrentes.application;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;

@Component
public class ConfGastoRecurrenteApplicationMapper {
	
    public ConfGastoRecurrenteResponse mapToResponse(ConfGastoRecurrente gastoRecurrente) {
    	LocalDateTime fechaModificacion = gastoRecurrente.getFechaModificacion() != null
                ? gastoRecurrente.getFechaModificacion().value()
                : null;
    	
    	return new ConfGastoRecurrenteResponse(
    			gastoRecurrente.getId(), 
    			gastoRecurrente.getUsuarioId().value(), 
    			gastoRecurrente.getDescripcion().value(),
    			gastoRecurrente.getCategoriaGastoId().value(), 
    			gastoRecurrente.getPeriodicidad().getFrecuencia().toString(),
        		gastoRecurrente.getPeriodicidad().getDiaVencimiento(),
        		gastoRecurrente.getPeriodicidad().getMesVencimiento(),
        		gastoRecurrente.getEstado().name(),
        		gastoRecurrente.getFechaCreacion().value(), 
        		fechaModificacion
        		);
    }
}
