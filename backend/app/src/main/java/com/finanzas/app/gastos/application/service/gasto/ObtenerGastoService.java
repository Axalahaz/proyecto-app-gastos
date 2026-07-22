package com.finanzas.app.gastos.application.service.gasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.presentation.user.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerGastoService {

    private final GastoRepository gastoRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final GastoApplicationMapper applicationMapper;

    public GastoResponse ejecutar(Long gastoId) {
    	
    	Long userId = usuarioAutenticado.obtenerId();
    	
    	Gasto gasto = gastoRepository
                .buscarPorIdYUsuarioId(gastoId, userId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto", gastoId));
    	
    	return applicationMapper.mapToResponse(gasto);
    }
}
