package com.finanzas.app.gastos.application.service.gasto;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.NotFoundException;

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

    private final GastoRepository gastoRepository;
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long gastoId) {

        Long userId = usuarioAutenticado.obtenerId();

        Gasto gasto = gastoRepository
                .buscarPorIdYUsuarioId(gastoId, userId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto", gastoId));
        
        LocalDate tiempoLimite = LocalDate.now();
        
        gasto.validarEliminacion(tiempoLimite);

        gastoRepository.eliminar(gasto.getId());
        
        log.info("Gasto {} eliminado correctamente", gasto.getId());
    }
}