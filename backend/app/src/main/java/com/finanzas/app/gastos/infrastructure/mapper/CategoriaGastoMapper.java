package com.finanzas.app.gastos.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.infrastructure.entity.CategoriaGastoEntityJPA;
import com.finanzas.app.shared.domain.vo.Fecha;

@Component
public class CategoriaGastoMapper {

    public CategoriaGastoEntityJPA mapToEntity(
    		CategoriaGasto categoriaGasto
    		) {
    	return CategoriaGastoEntityJPA.of(
    			categoriaGasto.getId(),
    			categoriaGasto.getUsuarioId(),
    			categoriaGasto.getNombre(),
    			categoriaGasto.getTipo(),
    			categoriaGasto.getFechaCreacion().getValue()
    			);
    }

    public CategoriaGasto mapToDomain(CategoriaGastoEntityJPA entity) {
    	return CategoriaGasto.reconstruir(
    			entity.getId(),
    			entity.getUsuarioId(),
    			entity.getNombre(),
    			entity.getTipo(),
    			new Fecha(entity.getFechaCreacion())
    			);
    }
    
}