package com.finanzas.app.gastos.categorias.infrastructure;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.UsuarioId;

@Component
public class CategoriaGastoMapper {

    public CategoriaGastoEntityJPA mapToEntity(
    		CategoriaGasto categoriaGasto
    		) {
    	return CategoriaGastoEntityJPA.of(
    			categoriaGasto.getId(),
    			categoriaGasto.getUsuarioId().value(),
    			categoriaGasto.getNombre(),
    			categoriaGasto.getTipo(),
    			categoriaGasto.getFechaCreacion().value()
    			);
    }

    public CategoriaGasto mapToDomain(CategoriaGastoEntityJPA entity) {
    	return CategoriaGasto.reconstruir(
    			entity.getId(),
    			new UsuarioId(entity.getUsuarioId()),
    			entity.getNombre(),
    			entity.getTipo(),
    			new Fecha(entity.getFechaCreacion())
    			);
    }
    
}