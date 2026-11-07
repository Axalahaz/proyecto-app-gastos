package com.finanzas.app.gastos.recurrentes.application.service.write;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationQuery;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.enums.Frecuencia;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.UsuarioId;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarConfGastoRecurrenteService {

    private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;
    private final ConfGastoRecurrenteApplicationMapper confGastoRecurrenteApplicationMapper;

    private final CategoriaGastoApplicationQuery categoriaQuery;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public ConfGastoRecurrenteResponse ejecutar(
    		Long categoriaGastoId,
    		String descripcion, 
    		Integer diaVencimiento,
    		Integer mesVencimiento,
    		Frecuencia frecuencia
    ) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
    	// categoria valida?
    	categoriaQuery.existePorId(categoriaGastoId, usuarioId);

        // ya existe la configuracion?        
        if (confGastoRecurrenteRepository.existePorDescripcion(descripcion, usuarioId)) {
        	throw new ConflictException("Ya existe una configuracion con ese nombre");
        }
        
        Fecha fechaCreacion = new Fecha(LocalDateTime.now());
        Descripcion descripcionVO = new Descripcion(descripcion);
        UsuarioId usuarioIdVO = new UsuarioId(usuarioId);
        CategoriaId categoriaIdVO = new CategoriaId(categoriaGastoId);
        
        ConfGastoRecurrente confGastoRecurrente = ConfGastoRecurrente.crear(
        		usuarioIdVO,
        		descripcionVO,
        		categoriaIdVO,
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento,
    		   	fechaCreacion
        );

        ConfGastoRecurrente guardado = confGastoRecurrenteRepository.guardar(confGastoRecurrente);
        
        log.info("Configuracion Gasto Recurrente registrado correctamente");
        
        return confGastoRecurrenteApplicationMapper.mapToResponse(guardado);
    }
}