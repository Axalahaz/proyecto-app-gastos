package com.finanzas.app.gastos.recurrentes.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.application.GastoRecurrenteQueryService;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteRepository;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class ActivarGastoRecurrenteService {

	private final GastoRecurrenteQueryService gastoRecurrenteQueryService;

    private final GastoRecurrenteRepository gastoRecurrenteRepository;

    public void ejecutar(Long gastoRecurrenteId) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteQueryService.obtenerPorId(gastoRecurrenteId);

        Fecha fechaCambioActivo = new Fecha(LocalDateTime.now());
        
        gastoRecurrente.activar(fechaCambioActivo);

        // JPA lo detecta como UPDATE porque ya existe
        gastoRecurrenteRepository.guardar(gastoRecurrente);
    }
}