package com.finanzas.app.gastos.application.mapper;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;

@Component
public class GastoApplicationMapper {
	
    public GastoResponse mapToResponse(Gasto gasto) {
    	return GastoResponse.of(
    			gasto.getId(), 
    			gasto.getCategoriaGastoId(),
    			gasto.getPlantillaId(),
    			gasto.getGastoRecurrenteId(),
        		gasto.getMonto().getValue(),
        		gasto.getDescripcion(), 
        		gasto.getEstado().toString(),
        		gasto.getFechaCreacion().getValue(),
        		
        		gasto.getFechaVencimiento() != null
                ? gasto.getFechaVencimiento().getValue()
                : null,
                
        		gasto.getFechaCambioEstado() != null
                ? gasto.getFechaCambioEstado().getValue()
                : null
        		);
    }
}
