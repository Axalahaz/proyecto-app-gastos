package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class QuitarPeriodicidadService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;

    public void ejecutar(Long gastoRecurrenteId) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteRepository
                .buscar(gastoRecurrenteId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));

        gastoRecurrente.quitarPeriodicidad();

        gastoRecurrenteRepository.guardar(gastoRecurrente);
        
        log.info("Gasto Recurrente {} se quito periodicidad correctamente", gastoRecurrente.getId());
    }
}