package com.finanzas.app.gastos.application.service.gasto;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.queryService.GastoQueryService;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Permite eliminar fisicamente un error durante un tiempo limitado
 * Despues solo se puede anular
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarGastoService {

	private final GastoQueryService gastoQueryService;
    private final GastoRepository gastoRepository;

    public void ejecutar(Long gastoId) {

        Gasto gasto = gastoQueryService.obtenerPorId(gastoId);
        
        LocalDate tiempoLimite = LocalDate.now();
        
        gasto.validarEliminacion(tiempoLimite);

        gastoRepository.eliminar(gasto.getId());
        
        log.info("Gasto {} eliminado correctamente", gasto.getId());
    }
}