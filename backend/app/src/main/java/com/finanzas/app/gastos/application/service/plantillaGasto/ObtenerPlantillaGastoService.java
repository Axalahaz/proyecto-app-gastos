package com.finanzas.app.gastos.application.service.plantillaGasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.PlantillaGastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.PlantillaGastoQueryService;
import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.PlantillaGastoResponse;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerPlantillaGastoService {

	private final PlantillaGastoApplicationMapper mapper;
	private final PlantillaGastoQueryService PlantillaGastoQueryService;

    public PlantillaGastoResponse ejecutar(Long plantillaId) {
    	
    	PlantillaGasto plantilla = PlantillaGastoQueryService.obtenerPorId(plantillaId);
	    		
    	return mapper.mapToResponse(plantilla);
    }
}
