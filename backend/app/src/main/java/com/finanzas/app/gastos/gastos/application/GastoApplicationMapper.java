package com.finanzas.app.gastos.gastos.application;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;

@Component
public class GastoApplicationMapper {
	
    public GastoResponse mapToResponse(Gasto gasto) {
    	return new GastoResponse(
    			gasto.getId(), 
    			gasto.getUsuarioId().value(), 
    			gasto.getImporte().value(),
        		gasto.getDescripcion().value(), 
        		gasto.getFechaCreacion().value(),
        		
        		gasto.getFechaModificacion() != null
        			? gasto.getFechaModificacion().value()
        			: null,

        		gasto.getFechaPagoRecurrente() != null
	                ? gasto.getFechaPagoRecurrente().value()
	                : null,
                
                gasto.getEstado().name(),
                gasto.getCategoriaGastoId().value(),
    			gasto.getConfGastoRecurrenteId()
       );
    }
}
