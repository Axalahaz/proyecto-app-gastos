package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.domain.model.Frecuencia;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarGastoRecurrenteConPeriodicidadService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper mapper;

    public GastoRecurrenteResponse ejecutar(
            Long gastoRecurrenteId,
            Integer nuevoDiaVencimiento,
            Integer nuevoMesVencimiento,
            Frecuencia nuevaFrecuencia
    		) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteRepository
                .buscar(gastoRecurrenteId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));
  
        
        // que parametro llego con valor para editar?
        
        gastoRecurrente.modificarPeriodicidad(
        		nuevaFrecuencia,
    			nuevoDiaVencimiento,
    			nuevoMesVencimiento
        );

        GastoRecurrente actualizado = gastoRecurrenteRepository.guardar(gastoRecurrente);

        return mapper.mapToResponse(actualizado);
    }
}