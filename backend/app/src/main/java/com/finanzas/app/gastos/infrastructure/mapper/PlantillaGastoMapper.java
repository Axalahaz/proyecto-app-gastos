package com.finanzas.app.gastos.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.infrastructure.entity.PlantillaGastoEntityJPA;
import com.finanzas.app.shared.domain.vo.Fecha;

@Component
public class PlantillaGastoMapper {

    public PlantillaGastoEntityJPA mapToEntity(PlantillaGasto plantilla) {
    	
        return PlantillaGastoEntityJPA.of(
        		plantilla.getId(),
        		plantilla.getCategoriaGastoId(),
        		plantilla.getDescripcion(), 
        		plantilla.getTipo(),
        		plantilla.getFechaCreacion().getValue()
        );

    }

    public PlantillaGasto mapToDomain(PlantillaGastoEntityJPA entity) {
    	
    	return PlantillaGasto.reconstruir(
    			entity.getId(),
    			entity.getCategoriaGastoId(),
    			entity.getDescripcion(),
    			entity.getTipo(),
    			new Fecha(entity.getFechaCreacion())
    			);
    }
    
}