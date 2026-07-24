package com.finanzas.app.gastos.application.queryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Service
public class GastoRecurrenteQueryService {

    private final GastoRecurrenteRepository repository;

    public GastoRecurrente obtenerPorId(Long id) {
        return repository.buscar(id)
                .orElseThrow(() -> NotFoundException.of("Gasto Recurrente", id));
    }
}