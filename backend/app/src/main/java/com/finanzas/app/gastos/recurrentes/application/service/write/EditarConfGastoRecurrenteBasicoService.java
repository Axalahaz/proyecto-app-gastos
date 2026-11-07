package com.finanzas.app.gastos.recurrentes.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationQuery;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarConfGastoRecurrenteBasicoService {

	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;

    private final ConfGastoRecurrenteRepository gastoRecurrenteRepository;
    private final ConfGastoRecurrenteApplicationMapper mapperConf;
    
    private final UsuarioAutenticado usuarioAutenticado;
    
    public ConfGastoRecurrenteResponse ejecutar(
            Long gastoRecurrenteId,
            String descripcion
    		) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
    	Descripcion descripcionVO = new Descripcion(descripcion);

    	ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteApplicationQuery
    			.obtenerPorId(gastoRecurrenteId, usuarioId);

        // ya existe un gasto recurrente igual?
        if (gastoRecurrenteRepository.existePorDescripcion(descripcion, usuarioId)) {
        	throw new ConflictException("Gasto Recurrente.");
        }
        
        // que parametro llego con valor para editar?
        
        confGastoRecurrente.editarDescripcion(descripcionVO);
 
        ConfGastoRecurrente actualizado = gastoRecurrenteRepository.guardar(confGastoRecurrente);

        return mapperConf.mapToResponse(actualizado);
    }
}