package com.finanzas.app.gastos.gastos.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.application.GastoQueryService;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteFactory;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteRepository;
import com.finanzas.app.shared.domain.model.Frecuencia;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.global.RecursoDuplicadoException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class VolverRecurrenteService {

	private final GastoQueryService gastoQueryService;

	private final GastoRepository gastoRepository;
    private final GastoApplicationMapper mapper;
    
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteFactory factory;
    
    public GastoResponse ejecutar(
            Long gastoId,
            Integer diaVencimiento,
            Integer mesVencimiento,
            Frecuencia frecuencia
    		) {

        Gasto gasto = gastoQueryService.obtenerPorId(gastoId);
   
        gasto.validarEstadoNoAnulado();
        
        if (gasto.esRecurrente()) throw new ConflictException(
        		"El gasto ya esa asociado a recurrente id " + gasto.getGastoRecurrenteId() + " " );
        
        boolean existe = gastoRecurrenteRepository
        		.existePorDescripcion(gasto.getDescripcion());
        
        if (existe) {
        	throw new RecursoDuplicadoException("Gasto Recurrente.");
        }
        
        Fecha fechaCreacion = new Fecha(LocalDateTime.now());

        GastoRecurrente gastoRecurrente = factory.of(
        		gasto.getCategoriaGastoId(),
        		null,
        		gasto.getDescripcion(), 
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento,
     		    fechaCreacion
        );

        gastoRecurrente = gastoRecurrenteRepository.guardar(gastoRecurrente);

        gasto.asociarAGastoRecurrente(gastoRecurrente.getId());

        Gasto actualizado = gastoRepository.guardar(gasto);

        return mapper.mapToResponse(actualizado);
    }
}