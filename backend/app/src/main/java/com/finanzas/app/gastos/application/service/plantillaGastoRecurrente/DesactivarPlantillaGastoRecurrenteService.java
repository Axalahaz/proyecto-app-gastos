package com.finanzas.app.gastos.application.service.plantillaGastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente.PlantillaGastoRecurrenteRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class DesactivarPlantillaGastoRecurrenteService {

	private final PlantillaGastoRecurrenteRepository plantillaRepository;

    public void ejecutar(Long plantillaId) {

    	PlantillaGastoRecurrente plantilla = plantillaRepository
                .buscarPorId(plantillaId)
                .orElseThrow(() ->
                		NotFoundException.of("Plantilla Gasto Recurrente", plantillaId));
        
        
    	plantilla.desactivar();

    	plantillaRepository.guardar(plantilla);
        
        log.info("Plantilla Gasto Recurrente {} desactivada correctamente", plantilla.getId());
    }
}