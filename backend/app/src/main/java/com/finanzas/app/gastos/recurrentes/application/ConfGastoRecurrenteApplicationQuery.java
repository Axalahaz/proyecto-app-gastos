package com.finanzas.app.gastos.recurrentes.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteRepository;
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