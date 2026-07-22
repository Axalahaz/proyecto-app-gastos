package com.finanzas.app.gastos.application.mapper;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.PlantillaGastoRecurrenteResponse;

@Component
public class PlantillaGastoRecurrenteApplicationMapper {
	
    public PlantillaGastoRecurrenteResponse mapToResponse(PlantillaGastoRecurrente plantilla) {
    	return PlantillaGastoRecurrenteResponse.of(
    			plantilla.getId(), 
    			plantilla.getDescripcion(),
    			plantilla.isActivo(),
        		plantilla.getFechaCreacion().getValue()
        		);
    }
}
