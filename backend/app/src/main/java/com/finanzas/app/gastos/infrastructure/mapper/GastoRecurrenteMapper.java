package com.finanzas.app.gastos.infrastructure.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.vo.Periodicidad;
import com.finanzas.app.gastos.infrastructure.entity.GastoRecurrenteEntityJPA;
import com.finanzas.app.shared.domain.vo.Fecha;

@Component
public class GastoRecurrenteMapper {

    public GastoRecurrenteEntityJPA mapToEntity(GastoRecurrente gastoRecurrente) {
    	
    	LocalDateTime fechaCambioActivo = gastoRecurrente.getFechaCambioActivo() != null
    			? gastoRecurrente.getFechaCambioActivo().getValue()
    			: null;
    	
        return GastoRecurrenteEntityJPA.of(
        		gastoRecurrente.getId(),
        		gastoRecurrente.getUserId(),
        		gastoRecurrente.getDescripcion(), 
        		gastoRecurrente.getCategoriaGastoId(), 
        		gastoRecurrente.getPeriodicidad().getDiaVencimiento(),
        		gastoRecurrente.getPeriodicidad().getMesVencimiento(),
        		gastoRecurrente.getPeriodicidad().getFrecuencia(),
        		gastoRecurrente.isPeriodicidadActiva(),
        		gastoRecurrente.isActivo(),
        		gastoRecurrente.getFechaCreacion().getValue(), 
        		fechaCambioActivo
        );

    }

    public GastoRecurrente mapToDomain(GastoRecurrenteEntityJPA entity) {
    	Fecha fechaCambioActivo = entity.getFechaCambioActivo() != null
    			? new Fecha(entity.getFechaCambioActivo())
    			: null;
    	
    	Periodicidad periodicidad = new Periodicidad(
    			entity.getFrecuencia(),
    			entity.getDiaVencimiento(),
    			entity.getMesVencimiento()
    			);
    	
    	return GastoRecurrente.reconstruir(
    			entity.getId(),
    			entity.getUsuarioId(),
    			entity.getDescripcion(),
    			entity.getCategoriaGastoId(),
    			periodicidad,
    			entity.isPeriodicidadActiva(),
    			entity.isActivo(),
    			new Fecha(entity.getFechaCreacion()),
    			fechaCambioActivo
    			);
    }
    
}