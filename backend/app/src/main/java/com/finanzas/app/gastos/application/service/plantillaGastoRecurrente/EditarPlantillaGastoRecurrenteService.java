package com.finanzas.app.gastos.application.service.plantillaGastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.GastoRecurrenteDuplicadoException;
import com.finanzas.app.gastos.application.mapper.PlantillaGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente.PlantillaGastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.PlantillaGastoRecurrenteResponse;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarPlantillaGastoRecurrenteService {

    private final PlantillaGastoRecurrenteApplicationMapper mapper;
    private final PlantillaGastoRecurrenteRepository plantillaRepository;


    public PlantillaGastoRecurrenteResponse ejecutar(
            Long plantillaId,
            String descripcion
    		) {

    	PlantillaGastoRecurrente plantilla = plantillaRepository
                .buscarPorId(plantillaId)
                .orElseThrow(() ->
                        NotFoundException.of("Plantilla Gasto Recurrente", plantillaId));
        
        // ya existe una plantilla igual?
        if (plantillaRepository.existePorDescripcion(descripcion)) {
        	throw new GastoRecurrenteDuplicadoException();
        }
        
        plantilla.editar(descripcion);
 
        PlantillaGastoRecurrente actualizado = plantillaRepository.guardar(plantilla);

        return mapper.mapToResponse(actualizado);
    }
}