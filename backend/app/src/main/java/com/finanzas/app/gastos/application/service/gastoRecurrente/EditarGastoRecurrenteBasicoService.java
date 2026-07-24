package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.GastoRecurrenteDuplicadoException;
import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarGastoRecurrenteBasicoService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper mapper;
    
    public GastoRecurrenteResponse ejecutar(
            Long gastoRecurrenteId,
            String descripcion
    		) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteRepository
                .buscar(gastoRecurrenteId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));
        
        // ya existe un gasto recurrente igual?
    	boolean existe = gastoRecurrenteRepository
             		.existePorDescripcion(descripcion);
         
        if (existe) {
        	throw new GastoRecurrenteDuplicadoException();
        }
        
        // que parametro llego con valor para editar?
        
        gastoRecurrente.editarBasico(
                descripcion
        );
 
        GastoRecurrente actualizado = gastoRecurrenteRepository.guardar(gastoRecurrente);

        return mapper.mapToResponse(actualizado);
    }
}