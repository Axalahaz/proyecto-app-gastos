package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.application.queryService.GastoRecurrenteQueryService;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.exception.global.RecursoDuplicadoException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarGastoRecurrenteBasicoService {

	private final GastoRecurrenteQueryService gastoRecurrenteQueryService;

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper mapper;
    
    public GastoRecurrenteResponse ejecutar(
            Long gastoRecurrenteId,
            String descripcion
    		) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteQueryService.obtenerPorId(gastoRecurrenteId);

        // ya existe un gasto recurrente igual?
        if (gastoRecurrenteRepository.existePorDescripcion(descripcion)) {
        	throw new RecursoDuplicadoException("Gasto Recurrente.");
        }
        
        // que parametro llego con valor para editar?
        
        gastoRecurrente.editarBasico(
                descripcion
        );
 
        GastoRecurrente actualizado = gastoRecurrenteRepository.guardar(gastoRecurrente);

        return mapper.mapToResponse(actualizado);
    }
}