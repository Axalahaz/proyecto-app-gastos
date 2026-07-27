package com.finanzas.app.gastos.application.service.plantillaGasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.PlantillaGastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.CategoriaGastoQueryService;
import com.finanzas.app.gastos.application.queryService.PlantillaGastoQueryService;
import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.domain.repository.plantillaGasto.PlantillaGastoRepository;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.PlantillaGastoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EditarPlantillaGastoService {

	private final PlantillaGastoRepository plantillaRepository;
	private final PlantillaGastoQueryService plantillaGastoQueryService;

	private final CategoriaGastoQueryService categoriaGastoQueryService;

    private final PlantillaGastoApplicationMapper mapper;

    public PlantillaGastoResponse ejecutar(
    		Long plantillaId,
    		Long nuevaCategoriaId,
    		String nombre
    ) {

    	PlantillaGasto plantilla = plantillaGastoQueryService.obtenerPorId(plantillaId);

    	if (nuevaCategoriaId != null) {
        	if (!plantilla.getCategoriaGastoId().equals(nuevaCategoriaId)) {
        		categoriaGastoQueryService.existePorId(nuevaCategoriaId);
        	}
        }
    	
    	plantilla.editar(nuevaCategoriaId, nombre);
    	
    	PlantillaGasto guardada = plantillaRepository.guardar(plantilla);
        
        log.info("Plantilla Gasto editado correctamente");
        
        return mapper.mapToResponse(guardada);
    }
}