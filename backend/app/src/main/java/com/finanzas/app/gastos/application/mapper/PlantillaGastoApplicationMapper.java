package com.finanzas.app.gastos.application.mapper;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.PlantillaGastoResponse;

@Component
public class PlantillaGastoApplicationMapper {

    public PlantillaGastoResponse mapToResponse(PlantillaGasto plantillaGasto) {
    	return PlantillaGastoResponse.of(
    			plantillaGasto.getId(),
    			plantillaGasto.getCategoriaGastoId(),
    			plantillaGasto.getDescripcion(),
    			plantillaGasto.getTipo(),
    			plantillaGasto.getFechaCreacion().getValue()
    			);
    }
}
