package com.finanzas.app.gastos.recurrentes.application.service.read;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationQuery;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerConfGastoRecurrenteService {
	
	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;
    private final ConfGastoRecurrenteApplicationMapper confGastoRecurrenteApplicationMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public ConfGastoRecurrenteResponse ejecutar(Long gastoRecurrenteId) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteApplicationQuery
        		.obtenerPorId(gastoRecurrenteId, usuarioId);
    	
    	return confGastoRecurrenteApplicationMapper.mapToResponse(confGastoRecurrente);
    }
}
