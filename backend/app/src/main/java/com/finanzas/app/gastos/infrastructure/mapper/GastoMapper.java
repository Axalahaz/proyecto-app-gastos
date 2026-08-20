package com.finanzas.app.gastos.infrastructure.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.infrastructure.entity.GastoEntityJPA;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;

@Component
public class GastoMapper {

    public GastoEntityJPA mapToEntity(Gasto gasto) {
    	
    	LocalDateTime fechaCambioEstado = gasto.getFechaCambioEstado() != null
    			? gasto.getFechaCambioEstado().getValue()
    			: null;

    	LocalDateTime fechaVencimiento = gasto.getFechaVencimiento() != null
    			? gasto.getFechaVencimiento().getValue()
    					: null;
    	
        return GastoEntityJPA.of(
        		gasto.getId(),
        		gasto.getCategoriaGastoId(), 
        		gasto.getPlantillaId(), 
        		gasto.getGastoRecurrenteId(), 
        		gasto.getMonto().getValue(),
        		gasto.getDescripcion(), 
        		gasto.getEstado(),
        		gasto.getFechaCreacion().getValue(), 
        		fechaVencimiento, 
        		fechaCambioEstado
        );

    }

    public Gasto mapToDomain(GastoEntityJPA entity) {
    	Fecha fechaCambioEstado = entity.getFechaCambioEstado() != null
    			? new Fecha(entity.getFechaCambioEstado())
    			: null;

    	Fecha fechaVencimiento = entity.getFechaVencimiento() != null
    			? new Fecha(entity.getFechaVencimiento())
    			: null;
    	
    	return Gasto.reconstruir(
    			entity.getId(),
    			entity.getCategoriaGastoId(),
    			entity.getPlantillaId(),
    			entity.getGastoRecurrenteId(),
    			new Money(entity.getMonto()),
    			entity.getDescripcion(),
    			entity.getEstado(),
    			new Fecha(entity.getFechaCreacion()),
    			fechaVencimiento,
    			fechaCambioEstado
    			);
    }
    
}