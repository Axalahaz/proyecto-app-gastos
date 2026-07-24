package com.finanzas.app.gastos.application.service.gasto;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.CategoriaGastoQueryService;
import com.finanzas.app.gastos.application.queryService.GastoQueryService;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.vo.Money;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarGastoService {

	private final CategoriaGastoQueryService categoriaQueryService;

	private final GastoQueryService gastoQueryService;
    private final GastoRepository gastoRepository;
    private final GastoApplicationMapper mapper;
    
    public GastoResponse ejecutar(
            Long gastoId,
            Long nuevaCategoriaId,
            BigDecimal monto,
            String descripcion
    		) {

        Gasto gasto = gastoQueryService.obtenerPorId(gastoId);
        
        if (nuevaCategoriaId != null) {
        	if (!gasto.getCategoriaGastoId().equals(nuevaCategoriaId)) {
            	categoriaQueryService.existePorId(nuevaCategoriaId);
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