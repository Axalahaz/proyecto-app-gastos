package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarGastoRecurrenteService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long gastoRecurrenteId) {

        Long userId = usuarioAutenticado.obtenerId();

        GastoRecurrente gastoRecurrente = gastoRecurrenteRepository
                .buscar(gastoRecurrenteId, userId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));
        
        if(gastoRecurrente.getUserId() == null) {
        	log.warn("No se pudo eliminar Gasto Recurrente (es del sistema)");
        	throw new ConflictException("No se puede borrar un gasto recurrente propio del sistema");
        }
        
        gastoRecurrente.validarPeriodicidadEstaActiva();

        gastoRecurrenteRepository.eliminar(gastoRecurrente.getId());
        
        log.info("Gasto Recurrente {} eliminado correctamente", gastoRecurrente.getId());
    }
}