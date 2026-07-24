package com.finanzas.app.gastos.application.service.gasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.GastoQueryService;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerGastoService {

	private final GastoQueryService gastoQueryService;
	
    private final GastoApplicationMapper applicationMapper;

    public GastoResponse ejecutar(Long gastoId) {
    	
        Gasto gasto = gastoQueryService.obtenerPorId(gastoId);
    	
    	return applicationMapper.mapToResponse(gasto);
    }
}
