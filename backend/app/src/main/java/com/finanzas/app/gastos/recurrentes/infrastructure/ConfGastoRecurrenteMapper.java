package com.finanzas.app.gastos.recurrentes.infrastructure;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.PeriodicidadVO;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.UsuarioId;

@Component
public class ConfGastoRecurrenteMapper {

    public ConfGastoRecurrenteEntityJPA mapToEntity(ConfGastoRecurrente gastoRecurrente) {
    	
    	LocalDateTime fechaModificacion = gastoRecurrente.getFechaModificacion() != null
    			? gastoRecurrente.getFechaModificacion().value()
    			: null;
    	
        return ConfGastoRecurrenteEntityJPA.of(
        		gastoRecurrente.getId(),
        		gastoRecurrente.getUsuarioId().value(),
        		gastoRecurrente.getDescripcion().value(), 
        		gastoRecurrente.getCategoriaGastoId().value(),
        		gastoRecurrente.getPeriodicidad().getFrecuencia(),
        		gastoRecurrente.getPeriodicidad().getDiaVencimiento(),
        		gastoRecurrente.getPeriodicidad().getMesVencimiento(),
        		gastoRecurrente.getEstado(),
        		gastoRecurrente.getFechaCreacion().value(), 
        		fechaModificacion
        );

    }

    public ConfGastoRecurrente mapToDomain(ConfGastoRecurrenteEntityJPA entity) {
    	Fecha getFechaModificacion = entity.getFechaModificacion() != null
    			? new Fecha(entity.getFechaModificacion())
    			: null;
    	
    	PeriodicidadVO periodicidad = new PeriodicidadVO(
    			entity.getFrecuencia(),
    			entity.getDiaVencimiento(),
    			entity.getMesVencimiento()
    			);
    	
    	return ConfGastoRecurrente.reconstruir(
    			entity.getId(),
    			new UsuarioId(entity.getUsuarioId()),
    			new Descripcion(entity.getDescripcion()),
    			new CategoriaId(entity.getCategoriaGastoId()),
    			periodicidad,
    			entity.getEstado(),
    			new Fecha(entity.getFechaCreacion()),
    			getFechaModificacion
    			);
    }
    
}