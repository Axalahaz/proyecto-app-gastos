package com.finanzas.app.gastos.application.service.plantillaGasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.queryService.PlantillaGastoQueryService;
import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.domain.repository.plantillaGasto.PlantillaGastoRepository;
import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.exception.extend.ConflictException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Permite eliminar fisicamente un error durante un tiempo limitado
 * Despues solo se puede anular
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarPlantillaGastoService {

	private final PlantillaGastoRepository plantillaRepository;
	private final PlantillaGastoQueryService plantillaGastoQueryService;

    public void ejecutar(Long plantillaId) {

    	PlantillaGasto plantilla = plantillaGastoQueryService.obtenerPorId(plantillaId);
    
    	if(plantilla.esTipo(TipoObjeto.SISTEMA)) {
    		throw new ConflictException(
    				"No se puede eliminar una Plantilla de Gasto propia del sistema");
    	}

    	plantillaRepository.eliminar(plantilla.getId());
        
        log.info("Plantilla Gasto {} eliminado correctamente", plantilla.getId());
    }
}