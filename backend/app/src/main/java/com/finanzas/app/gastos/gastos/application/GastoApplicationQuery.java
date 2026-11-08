package com.finanzas.app.gastos.gastos.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Service
public class GastoApplicationQuery {

    private final GastoRepository gastoRepository;

    // - Consideran el usuario al que pertenece.
    
    public Gasto obtenerPorId(Long gastoId, Long usuarioId) {
        return gastoRepository.buscarPorId(gastoId, usuarioId)
                .orElseThrow(() -> NotFoundException.of("Gasto", gastoId));
    }
}