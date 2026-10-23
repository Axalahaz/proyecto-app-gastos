package com.finanzas.app.gastos.gastos.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.application.GastoQueryService;
import com.finanzas.app.gastos.gastos.domain.Gasto;
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
