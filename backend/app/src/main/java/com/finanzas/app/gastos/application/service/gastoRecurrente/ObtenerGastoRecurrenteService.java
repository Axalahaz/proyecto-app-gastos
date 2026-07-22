package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.user.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerGastoRecurrenteService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final GastoRecurrenteApplicationMapper gastoRecurrenteApplicationMapper;

    public GastoRecurrenteResponse ejecutar(Long gastoRecurrenteId) {
    	
    	Long userId = usuarioAutenticado.obtenerId();
    	
    	GastoRecurrente gastoRecurrente = gastoRecurrenteRepository.buscar(gastoRecurrenteId, userId)
    			.orElseThrow(() -> NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));
                        
    	
    	return gastoRecurrenteApplicationMapper.mapToResponse(gastoRecurrente);
    }
}
