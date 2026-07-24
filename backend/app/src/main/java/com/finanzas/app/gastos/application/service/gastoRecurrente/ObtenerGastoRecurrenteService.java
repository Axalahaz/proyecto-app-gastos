package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.application.queryService.GastoRecurrenteQueryService;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;

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
