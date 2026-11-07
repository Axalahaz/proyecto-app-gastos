package com.finanzas.app.gastos.recurrentes.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.recurrentes.application.GastoRecurrenteQueryService;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteRepository;
import com.finanzas.app.shared.exception.extend.ConflictException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarGastoRecurrenteService {
	
	private final GastoRecurrenteQueryService gastoRecurrenteQueryService;

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRepository gastoRepository;

    public void ejecutar(Long gastoRecurrenteId) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteQueryService.obtenerPorId(gastoRecurrenteId);

        if (gastoRepository.existePorGastoRecurrenteId(gastoRecurrente.getId())) {
            throw new ConflictException(
            	"El gasto recurrente ya posee gastos asociados y solo puede desactivarse."
            );
        }
        
        LocalDate tiempoLimite = LocalDate.now();
        
        gastoRecurrente.validarEliminacion(tiempoLimite);

        gastoRecurrenteRepository.eliminar(gastoRecurrente.getId());
        
        log.info("Gasto Recurrente {} eliminado correctamente", gastoRecurrente.getId());
    }
}