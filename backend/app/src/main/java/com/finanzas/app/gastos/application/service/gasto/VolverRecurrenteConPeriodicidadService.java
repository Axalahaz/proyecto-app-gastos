package com.finanzas.app.gastos.application.service.gasto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.GastoRecurrenteDuplicadoException;
import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.Frecuencia;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.factory.GastoRecurrenteFactory;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.user.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.NotFoundException;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class VolverRecurrenteConPeriodicidadService {

    private final GastoRepository gastoRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final GastoApplicationMapper mapper;
    
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteFactory factory;
    
    public GastoResponse ejecutar(
            Long gastoId,
            Integer diaVencimiento,
            Integer mesVencimiento,
            Frecuencia frecuencia
    		) {

        Long userId = usuarioAutenticado.obtenerId();

        Gasto gasto = gastoRepository
                .buscarPorIdYUsuarioId(gastoId, userId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto", gastoId));
        
   
        gasto.validarEstadoNoAnulado();
        
        if (gasto.esRecurrente()) throw ValidationException.of(
        		"El gasto ya esa asociado a recurrente id " + gasto.getGastoRecurrenteId());
        
        boolean existe = gastoRecurrenteRepository
        		.existePorDescripcionYCategoriaGastoId(
        				gasto.getDescripcion(),
        				gasto.getCategoriaGastoId()
        				);
        
        if (existe) {
        	throw new GastoRecurrenteDuplicadoException();
        }
        
        Fecha fechaCreacion = new Fecha(LocalDateTime.now());

        GastoRecurrente gastoRecurrente = factory.of(
        		userId,
        		gasto.getDescripcion(), 
        		gasto.getCategoriaGastoId(),
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento,
    		   	true,
     		    fechaCreacion
        );
        

        gastoRecurrente = gastoRecurrenteRepository.guardar(gastoRecurrente);

        gasto.asociarAGastoRecurrente(gastoRecurrente.getId());

        Gasto actualizado = gastoRepository.guardar(gasto);

        return mapper.mapToResponse(actualizado);
    }
}