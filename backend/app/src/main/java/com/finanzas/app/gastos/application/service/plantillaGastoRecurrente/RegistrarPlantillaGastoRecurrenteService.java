package com.finanzas.app.gastos.application.service.plantillaGastoRecurrente;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.PlantillaGastoRecurrenteDuplicadaException;
import com.finanzas.app.gastos.application.mapper.PlantillaGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente.PlantillaGastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.PlantillaGastoRecurrenteResponse;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarPlantillaGastoRecurrenteService {

	private final PlantillaGastoRecurrenteRepository plantillaRepository;
    private final PlantillaGastoRecurrenteApplicationMapper plantillaMapper;
    
    public PlantillaGastoRecurrenteResponse ejecutar(
    		String descripcion,
    		boolean activo
    		) {

    	// control de consistencia
        if (plantillaRepository.existePorDescripcion(descripcion)) {
        	throw new PlantillaGastoRecurrenteDuplicadaException();
        }
        
        PlantillaGastoRecurrente plantilla = PlantillaGastoRecurrente.crear(
        		descripcion,
        		activo,
        		new Fecha(LocalDateTime.now())
        );

        PlantillaGastoRecurrente guardado = plantillaRepository.guardar(plantilla);
        
        log.info("Plantilla Gasto Recurrente registrado correctamente");
        
        return plantillaMapper.mapToResponse(guardado);
    }
}