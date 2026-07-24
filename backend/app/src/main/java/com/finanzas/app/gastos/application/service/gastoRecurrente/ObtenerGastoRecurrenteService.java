package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerGastoRecurrenteService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper gastoRecurrenteApplicationMapper;

    public GastoRecurrenteResponse ejecutar(Long gastoRecurrenteId) {
    	
    	GastoRecurrente gastoRecurrente = gastoRecurrenteRepository.buscar(gastoRecurrenteId)
    			.orElseThrow(() -> NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));
                        
    	
    	return gastoRecurrenteApplicationMapper.mapToResponse(gastoRecurrente);
    }
}
