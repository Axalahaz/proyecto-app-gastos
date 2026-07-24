package com.finanzas.app.gastos.application.queryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Service
public class GastoQueryService {

    private final GastoRepository gastoRepository;

    public Gasto obtenerPorId(Long id) {
        return gastoRepository.buscarPorId(id)
                .orElseThrow(() -> NotFoundException.of("Gasto", id));
    }
}