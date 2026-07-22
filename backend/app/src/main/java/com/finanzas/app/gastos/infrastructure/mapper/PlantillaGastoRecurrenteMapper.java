package com.finanzas.app.gastos.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.infrastructure.entity.PlantillaGastoRecurrenteEntityJPA;
import com.finanzas.app.shared.domain.vo.Fecha;

@Component
public class PlantillaGastoRecurrenteMapper {

    public PlantillaGastoRecurrenteEntityJPA mapToEntity(PlantillaGastoRecurrente plantilla) {
    	
        return PlantillaGastoRecurrenteEntityJPA.of(
        		plantilla.getId(),
        		plantilla.getDescripcion(), 
        		plantilla.isActivo(),
        		plantilla.getFechaCreacion().getValue()
        );

    }

    public PlantillaGastoRecurrente mapToDomain(PlantillaGastoRecurrenteEntityJPA entity) {
    	
    	return PlantillaGastoRecurrente.reconstruir(
    			entity.getId(),
    			entity.getDescripcion(),
    			entity.isActivo(),
    			new Fecha(entity.getFechaCreacion())
    			);
    }
    
}