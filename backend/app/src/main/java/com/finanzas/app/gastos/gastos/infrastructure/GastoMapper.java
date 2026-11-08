package com.finanzas.app.gastos.gastos.infrastructure;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.domain.vo.UsuarioId;

@Component
public class GastoMapper {

    public GastoEntityJPA mapToEntity(Gasto gasto) {
    	
    	LocalDateTime fechaModificacion = gasto.getFechaModificacion() != null
    			? gasto.getFechaModificacion().value()
    			: null;

    	LocalDateTime fechaPagoRecurrente = gasto.getFechaPagoRecurrente() != null
    			? gasto.getFechaPagoRecurrente().value()
    			: null;
    	
        return GastoEntityJPA.of(
        		gasto.getId(),
        		gasto.getUsuarioId().value(),
        		gasto.getImporte().value(),
        		gasto.getDescripcion().value(), 
        		gasto.getFechaCreacion().value(), 
        		fechaModificacion,
        		fechaPagoRecurrente, 
        		gasto.getEstado(),
        		gasto.getCategoriaGastoId().value(),
        		gasto.getConfGastoRecurrenteId()
        );

    }

    public Gasto mapToDomain(GastoEntityJPA entity) {
    	Fecha fechaModificacion = entity.getFechaModificacion() != null
    			? new Fecha(entity.getFechaModificacion())
    			: null;

    	Fecha fechaPagoRecurrente = entity.getFechaPagoRecurrente() != null
    			? new Fecha(entity.getFechaPagoRecurrente())
    			: null;
    	
    	return Gasto.reconstruir(
    			entity.getId(),
    			new UsuarioId(entity.getUsuarioId()),
    			new Money(entity.getImporte()),
    			new Descripcion(entity.getDescripcion()),
    			new Fecha(entity.getFechaCreacion()),
    			fechaModificacion,
    			fechaPagoRecurrente,
    			entity.getEstado(),
    			new CategoriaId(entity.getCategoriaGastoId()),
    			entity.getConfGastoRecurrenteId()
    			);
    }
    
}