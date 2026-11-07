package com.finanzas.app.gastos.recurrentes.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.domain.repository.plantillaGasto.PlantillaGastoRepository;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.gastos.recurrentes.application.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteFactory;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteRepository;
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

    private final PlantillaGastoRepository plantillaRepository;
    
    private final GastoRecurrenteFactory factory;
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper gastoRecurrenteApplicationMapper;

    private final CategoriaGastoRepository categoriaRepository;

    public GastoRecurrenteResponse ejecutar(
    		Long categoriaGastoId,
    		Long plantillaId,
    		String descripcion, 
    		Integer diaVencimiento,
    		Integer mesVencimiento,
    		Frecuencia frecuencia
    ) {

    	// control de consistencia
    	Long idCategoria = null;

    	if (categoriaGastoId != null && categoriaRepository.existePorId(categoriaGastoId)) {
    		idCategoria = categoriaGastoId;
    	}

    	Long idPlantilla = null;
    	
    	if (plantillaId != null && plantillaRepository.existePorId(plantillaId)) {
    		PlantillaGasto plantilla = plantillaRepository.buscarPorId(plantillaId).orElse(null);
    		if(plantilla != null) {
    			idPlantilla = plantilla.getId();
    			idCategoria = plantilla.getCategoriaGastoId();
    		}
    	}

        // ya existe?        
        if (gastoRecurrenteRepository.existePorDescripcion(descripcion)) {
        	throw new RecursoDuplicadoException("Gasto Recurrente.");
        }
        
        GastoRecurrente gastoRecurrente = factory.of(
        		idCategoria,
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