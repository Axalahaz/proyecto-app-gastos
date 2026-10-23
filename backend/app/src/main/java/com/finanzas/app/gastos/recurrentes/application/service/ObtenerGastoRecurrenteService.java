package com.finanzas.app.gastos.recurrentes.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.gastos.recurrentes.application.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.application.GastoRecurrenteQueryService;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrente;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerGastoRecurrenteService {
	
	private final GastoRecurrenteQueryService gastoRecurrenteQueryService;

    private final GastoRecurrenteApplicationMapper gastoRecurrenteApplicationMapper;

    public GastoRecurrenteResponse ejecutar(Long gastoRecurrenteId) {
    	
        GastoRecurrente gastoRecurrente = gastoRecurrenteQueryService.obtenerPorId(gastoRecurrenteId);
    	
    	return gastoRecurrenteApplicationMapper.mapToResponse(gastoRecurrente);
    }
}
