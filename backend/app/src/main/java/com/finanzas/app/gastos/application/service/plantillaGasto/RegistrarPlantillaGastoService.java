package com.finanzas.app.gastos.application.service.plantillaGasto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.PlantillaGastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.CategoriaGastoQueryService;
import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.domain.repository.plantillaGasto.PlantillaGastoRepository;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.PlantillaGastoResponse;
import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ConflictException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarPlantillaGastoService {

	private final PlantillaGastoRepository plantillaRepository;
	private final PlantillaGastoApplicationMapper mapper;
	
    private final CategoriaGastoQueryService categoriaQueryService;

    public PlantillaGastoResponse ejecutar(
    		Long categoriaGastoId,
    		String descripcion
    ) {

    	categoriaQueryService.existePorId(categoriaGastoId);
    	
        if (!plantillaRepository.existePorDescripcionYCategoriaGastoId(descripcion, categoriaGastoId)) {
             throw new ConflictException("El Recurso Plantilla gasto con el nombre " + descripcion + 
            		 " y la categoria id " + categoriaGastoId + " ya existe.");
         }
    	
        PlantillaGasto plantilla = PlantillaGasto.crear(
        		categoriaGastoId, 
        		descripcion, 
        		TipoObjeto.USUARIO, 
        		new Fecha(LocalDateTime.now())
        );
        		
        PlantillaGasto guardada =  plantillaRepository.guardar(plantilla);
        
        log.info("Plantilla Gasto registrada correctamente");
        
        return mapper.mapToResponse(guardada);
    }
}