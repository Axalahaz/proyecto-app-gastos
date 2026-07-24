package com.finanzas.app.gastos.application.service.gasto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.queryService.GastoQueryService;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class AnularGastoService {

	private final GastoQueryService gastoQueryService;
    private final GastoRepository gastoRepository;

    public void ejecutar(Long gastoId) {

        Gasto gasto = gastoQueryService.obtenerPorId(gastoId);

        Fecha fechaCambioEstado = new Fecha(LocalDateTime.now());
        
        gasto.anular(fechaCambioEstado);

        gastoRepository.guardar(gasto);
        
        log.info("Gasto {} anulado correctamente", gasto.getId());
    }
}