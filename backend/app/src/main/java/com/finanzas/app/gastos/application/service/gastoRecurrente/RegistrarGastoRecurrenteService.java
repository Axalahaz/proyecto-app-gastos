package com.finanzas.app.gastos.application.service.gastoRecurrente;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.GastoRecurrenteDuplicadoException;
import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.factory.GastoRecurrenteFactory;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.domain.model.Frecuencia;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarGastoRecurrenteService {

    private final GastoRecurrenteFactory factory;
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper gastoRecurrenteApplicationMapper;


    public GastoRecurrenteResponse ejecutar(
    		String descripcion, 
    		Integer diaVencimiento,
    		Integer mesVencimiento,
    		Frecuencia frecuencia
    		) {

        // ya existe?        
        boolean	existe = gastoRecurrenteRepository
            		.existePorDescripcion(descripcion);
        
        if (existe) {
        	throw new GastoRecurrenteDuplicadoException();
        }
        
        GastoRecurrente gastoRecurrente = factory.of(
        		descripcion,
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento,
    		   	true,
    		   	new Fecha(LocalDateTime.now())
        );

        GastoRecurrente guardado = gastoRecurrenteRepository.guardar(gastoRecurrente);
        
        log.info("Gasto Recurrente registrado correctamente");
        
        return gastoRecurrenteApplicationMapper.mapToResponse(guardado);
    }
}