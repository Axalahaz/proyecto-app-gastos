package com.finanzas.app.gastos.application.service.plantillaGastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.PlantillaGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente.PlantillaGastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.PlantillaGastoRecurrenteResponse;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerPlantillaGastoRecurrenteService {

	private final PlantillaGastoRecurrenteRepository plantillaRepository;
    private final PlantillaGastoRecurrenteApplicationMapper mapper;

    public PlantillaGastoRecurrenteResponse ejecutar(Long plantillaId) {
    	
    	PlantillaGastoRecurrente plantilla = plantillaRepository
                .buscarPorId(plantillaId)
                .orElseThrow(() ->
                        NotFoundException.of("Plantilla Gasto Recurrente", plantillaId));
        
    	
    	return mapper.mapToResponse(plantilla);
    }
}
