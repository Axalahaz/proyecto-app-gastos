package com.finanzas.app.gastos.application.service.gastoRecurrente;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.GastoRecurrenteDuplicadoException;
import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.entity.Frecuencia;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.factory.GastoRecurrenteFactory;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.user.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarGastoRecurrenteService {

    private final CategoriaGastoRepository categoriaRepository;
    
    private final GastoRecurrenteFactory factory;
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper gastoRecurrenteApplicationMapper;

    private final UsuarioAutenticado usuarioAutenticado;
    

    public GastoRecurrenteResponse ejecutar(
    		String descripcion, 
    		Long categoriaGastoId,
    		Integer diaVencimiento,
    		Integer mesVencimiento,
    		Frecuencia frecuencia
    		) {

    	Long userId = usuarioAutenticado.obtenerId();
    	
    	// control de consistencia
    	CategoriaGasto categoria = categoriaRepository
                .buscarDisponible(categoriaGastoId, userId)
                .orElseThrow(() -> NotFoundException.of("Categoría", categoriaGastoId));

        // ya existe?        
        boolean	existe = gastoRecurrenteRepository
            		.existePorDescripcionYCategoriaGastoId(descripcion, categoria.getId());
        
        if (existe) {
        	throw new GastoRecurrenteDuplicadoException();
        }
        
        GastoRecurrente gastoRecurrente = factory.of(
        		userId,
        		descripcion,
     		    categoria.getId(),
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