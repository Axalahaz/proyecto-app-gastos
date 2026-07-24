package com.finanzas.app.gastos.application.service.gasto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.GastoRecurrenteDuplicadoException;
import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.factory.GastoRecurrenteFactory;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.NotFoundException;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class VolverRecurrenteService {

    private final GastoRepository gastoRepository;
    private final GastoApplicationMapper mapper;
    
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteFactory factory;
    
    public GastoResponse ejecutar(Long gastoId) {

        Gasto gasto = gastoRepository
                .buscarPorId(gastoId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto", gastoId));
        
   
        gasto.validarEstadoNoAnulado();
        
        if (gasto.esRecurrente()) throw ValidationException.of(
        		"El gasto ya esa asociado a recurrente id " + gasto.getGastoRecurrenteId());
        
        boolean existe = gastoRecurrenteRepository
        		.existePorDescripcion(gasto.getDescripcion());
        
        if (existe) {
        	throw new GastoRecurrenteDuplicadoException();
        }
        
        Fecha fechaCreacion = new Fecha(LocalDateTime.now());

        GastoRecurrente gastoRecurrente = factory.of(
        		gasto.getDescripcion(), 
        		null,
        		null,
        		null,
        		false,
     		    fechaCreacion
        );
        

        gastoRecurrente = gastoRecurrenteRepository.guardar(gastoRecurrente);

        gasto.asociarAGastoRecurrente(gastoRecurrente.getId());

        Gasto actualizado = gastoRepository.guardar(gasto);

        return mapper.mapToResponse(actualizado);
    }
}