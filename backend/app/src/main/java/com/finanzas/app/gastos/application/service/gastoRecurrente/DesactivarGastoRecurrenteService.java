package com.finanzas.app.gastos.application.service.gastoRecurrente;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.queryService.GastoRecurrenteQueryService;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class DesactivarGastoRecurrenteService {

	private final GastoRecurrenteQueryService gastoRecurrenteQueryService;

    private final GastoRecurrenteRepository gastoRecurrenteRepository;

    public void ejecutar(Long gastoRecurrenteId) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteQueryService.obtenerPorId(gastoRecurrenteId);

        Fecha fechaCambioActivo = new Fecha(LocalDateTime.now());
        
        gastoRecurrente.desactivar(fechaCambioActivo);

        gastoRecurrenteRepository.guardar(gastoRecurrente);
        
        log.info("Gasto Recurrente {} desactivado correctamente", gastoRecurrente.getId());
    }
}