package com.finanzas.app.gastos.application.service.gasto;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarGastoService {

    private final GastoRepository gastoRepository;
    private final CategoriaGastoRepository categoriaRepository;
    private final GastoApplicationMapper mapper;
    
    public GastoResponse ejecutar(
            Long gastoId,
            Long nuevaCategoriaId,
            BigDecimal monto,
            String descripcion
    		) {

        Gasto gasto = gastoRepository
                .buscarPorId(gastoId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto", gastoId));
        
        if (nuevaCategoriaId != null) {
        	if (!gasto.getCategoriaGastoId().equals(nuevaCategoriaId)) {
        		
        		boolean categoriaNueva = categoriaRepository
        				.existePorId(nuevaCategoriaId);
        		
        		if (!categoriaNueva) {
        			throw NotFoundException.of("Categoría", nuevaCategoriaId);
        		}
        	}
        }
        
        Money money = monto != null ? new Money(monto) : null;

        gasto.editar(
                nuevaCategoriaId,
                money,
                descripcion
        );
        
        Gasto actualizado = gastoRepository.guardar(gasto);

        return mapper.mapToResponse(actualizado);
    }
}