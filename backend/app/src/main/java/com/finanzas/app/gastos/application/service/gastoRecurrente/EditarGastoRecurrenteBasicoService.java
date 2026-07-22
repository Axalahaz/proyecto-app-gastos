package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.exception.GastoRecurrenteDuplicadoException;
import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.user.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarGastoRecurrenteBasicoService {

	private final CategoriaGastoRepository categoriaRepository;

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper mapper;
    
    private final UsuarioAutenticado usuarioAutenticado;


    public GastoRecurrenteResponse ejecutar(
            Long gastoRecurrenteId,
            String descripcion,
            Long nuevaCategoriaId
    		) {

        Long userId = usuarioAutenticado.obtenerId();

        GastoRecurrente gastoRecurrente = gastoRecurrenteRepository
                .buscar(gastoRecurrenteId, userId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));
        
        // si se cambia la categoria:
        if (nuevaCategoriaId != null &&
        		!gastoRecurrente.getCategoriaGastoId().equals(nuevaCategoriaId)) {
        	
        	boolean categoriaNueva = categoriaRepository
        			.existePorIdYUsuarioId(nuevaCategoriaId, userId);
        	
        	if (!categoriaNueva) {
        		throw NotFoundException.of("Categoría", nuevaCategoriaId);
        	}
        }
        
        // ya existe un gasto recurrente igual?
    	boolean existe = gastoRecurrenteRepository
             		.existePorDescripcionYCategoriaGastoId(descripcion, nuevaCategoriaId);
         
        if (existe) {
        	throw new GastoRecurrenteDuplicadoException();
        }
        
        // que parametro llego con valor para editar?
        
        gastoRecurrente.editarBasico(
                descripcion,
                nuevaCategoriaId
        );
 
        GastoRecurrente actualizado = gastoRecurrenteRepository.guardar(gastoRecurrente);

        return mapper.mapToResponse(actualizado);
    }
}