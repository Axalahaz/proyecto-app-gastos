package com.finanzas.app.gastos.gastos.application.service.read;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.application.GastoApplicationQuery;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerGastoQueryService {

	private final GastoApplicationQuery gastoApplicationQuery;
    private final GastoApplicationMapper applicationMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public GastoResponse ejecutar(Long gastoId) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        Gasto gasto = gastoApplicationQuery.obtenerPorId(gastoId, usuarioId);
    	
    	return applicationMapper.mapToResponse(gasto);
    }
}
