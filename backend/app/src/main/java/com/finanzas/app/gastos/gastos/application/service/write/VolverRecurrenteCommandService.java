package com.finanzas.app.gastos.gastos.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.application.GastoApplicationQuery;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;
import com.finanzas.app.gastos.recurrentes.application.contract.ConfGastoRecurrenteApplication;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.enums.Frecuencia;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class VolverRecurrenteCommandService {

	private final GastoApplicationQuery gastoApplicationQuery;
	private final GastoRepository gastoRepository;
    private final GastoApplicationMapper gastoApplicationMapper;
    
    private final ConfGastoRecurrenteApplication confGastoRecurrenteApplication;
    
    private final UsuarioAutenticado usuarioAutenticado;
    
    public GastoResponse ejecutar(
            Long gastoId,
            Integer diaVencimiento,
            Integer mesVencimiento,
            Frecuencia frecuencia
    		) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        Gasto gasto = gastoApplicationQuery.obtenerPorId(gastoId, usuarioId);
   
        gasto.validarEstadoActivo();
        
        // Valida si ya esta asociado a una configuracion
        if (gasto.esRecurrente()) 
        	throw new ConflictException("El gasto ya esa asociado a una configuracion recurrente");
        
        // Verifica duplicados
        boolean existe = confGastoRecurrenteApplication.existePorDescripcion(
		    gasto.getDescripcion().value(),
		    usuarioId
		);
		
		if (existe) {
		    throw new ConflictException("Ya existe una configuracion con esa descripcion");
		}
        
		// Comunica con creacion configuracion recurrencia y entrega el id
        Long confGastoRecurrenteId = confGastoRecurrenteApplication.crearDesdeGasto(
        		gasto.getUsuarioId().value(),
        		gasto.getDescripcion().value(), 
        		gasto.getCategoriaGastoId().value(),
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento
        );

        gasto.asociarAGastoRecurrente(confGastoRecurrenteId);

        Gasto actualizado = gastoRepository.guardar(gasto);

        return gastoApplicationMapper.mapToResponse(actualizado);
    }
}