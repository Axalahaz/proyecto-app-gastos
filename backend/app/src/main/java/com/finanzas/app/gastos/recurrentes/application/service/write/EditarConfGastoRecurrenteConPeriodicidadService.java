package com.finanzas.app.gastos.recurrentes.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationQuery;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.enums.Frecuencia;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarConfGastoRecurrenteConPeriodicidadService {

	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;
	private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;
    private final ConfGastoRecurrenteApplicationMapper confGastoRecurrenteApplicationMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public ConfGastoRecurrenteResponse ejecutar(
            Long gastoRecurrenteId,
            Integer nuevoDiaVencimiento,
            Integer nuevoMesVencimiento,
            Frecuencia nuevaFrecuencia
    		) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteApplicationQuery
        		.obtenerPorId(gastoRecurrenteId, usuarioId);
  
        // que parametro llego con valor para editar?
        confGastoRecurrente.modificarPeriodicidad(
        		nuevaFrecuencia,
    			nuevoDiaVencimiento,
    			nuevoMesVencimiento
        );

        ConfGastoRecurrente actualizado = confGastoRecurrenteRepository.guardar(confGastoRecurrente);

        return confGastoRecurrenteApplicationMapper.mapToResponse(actualizado);
    }
}