package com.finanzas.app.gastos.application.service.gastoRecurrente;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.factory.GastoRecurrenteFactory;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente.PlantillaGastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.domain.model.Frecuencia;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.global.RecursoDuplicadoException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarGastoRecurrenteService {

    private final PlantillaGastoRecurrenteRepository plantillaRepository;
    
    private final GastoRecurrenteFactory factory;
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper gastoRecurrenteApplicationMapper;


    public GastoRecurrenteResponse ejecutar(
    		Long plantillaId,
    		String descripcion, 
    		Integer diaVencimiento,
    		Integer mesVencimiento,
    		Frecuencia frecuencia
    ) {

    	Long idPlantilla = null;
    	
    	if (plantillaId != null && plantillaRepository.existePorId(plantillaId)) {
    	    idPlantilla = plantillaId;
    	}

        // ya existe?        
        if (gastoRecurrenteRepository.existePorDescripcion(descripcion)) {
        	throw new RecursoDuplicadoException("Gasto Recurrente.");
        }
        
        GastoRecurrente gastoRecurrente = factory.of(
        		idPlantilla,
        		descripcion,
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento,
    		   	new Fecha(LocalDateTime.now())
        );

        GastoRecurrente guardado = gastoRecurrenteRepository.guardar(gastoRecurrente);
        
        log.info("Gasto Recurrente registrado correctamente");
        
        return gastoRecurrenteApplicationMapper.mapToResponse(guardado);
    }
}