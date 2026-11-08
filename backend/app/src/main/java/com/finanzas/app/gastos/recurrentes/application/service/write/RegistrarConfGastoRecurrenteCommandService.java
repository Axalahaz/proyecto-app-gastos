package com.finanzas.app.gastos.recurrentes.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoApplication;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationQuery;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteFactory;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.enums.Frecuencia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarConfGastoRecurrenteCommandService {

	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;
    private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;
    private final ConfGastoRecurrenteApplicationMapper confGastoRecurrenteApplicationMapper;
    private final ConfGastoRecurrenteFactory confGastoRecurrenteFactory;

    private final CategoriaGastoApplication categoriaGastoApplication;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public ConfGastoRecurrenteResponse ejecutar(
    		Long categoriaGastoId,
    		String descripcion, 
    		Integer diaVencimiento,
    		Integer mesVencimiento,
    		Frecuencia frecuencia
    ) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
    	// Verifica existencia de categoria
    	categoriaGastoApplication.existePorId(categoriaGastoId, usuarioId);

        // Verifica la existencia de duplicados      
    	confGastoRecurrenteApplicationQuery.existePorDescripcion(descripcion, usuarioId);
        
        ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteFactory.crear(
        		usuarioId,
        		descripcion,
        		categoriaGastoId,
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento
        );

        ConfGastoRecurrente guardado = confGastoRecurrenteRepository.guardar(confGastoRecurrente);
        
        log.info("Configuracion Gasto Recurrente registrado correctamente");
        
        return confGastoRecurrenteApplicationMapper.mapToResponse(guardado);
    }
}