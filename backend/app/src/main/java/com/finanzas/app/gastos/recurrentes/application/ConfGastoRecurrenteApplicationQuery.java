package com.finanzas.app.gastos.recurrentes.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Service
public class ConfGastoRecurrenteApplicationQuery {

    private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;

    // Consideran el usuario al que pertenece la categoría.

    public ConfGastoRecurrente obtenerPorId(Long gastoRecurrenteId, Long usuarioId) {
        return confGastoRecurrenteRepository.buscarPorId(gastoRecurrenteId, usuarioId)
                .orElseThrow(() -> NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));
    }
    
    public void existePorId(Long gastoRecurrenteId, Long usuarioId) {
    	if (!confGastoRecurrenteRepository.existePorId(gastoRecurrenteId, usuarioId)) {
            throw NotFoundException.of("Gasto Recurrente", gastoRecurrenteId);
        }    
    }

    public void existePorDescripcion(String descripcion, Long usuarioId) {
    	if (confGastoRecurrenteRepository.existePorDescripcion(descripcion, usuarioId)) {
        	throw new ConflictException("Ya existe una configuracion con ese nombre");
        }  
    }
}