package com.finanzas.app.gastos.categorias.application.service.write;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.gastos.categorias.presentation.dto.CategoriaGastoResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.enums.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarCategoriaGastoCommandService {

    private final CategoriaGastoRepository categoriaRepository;
    private final CategoriaGastoApplicationMapper categoriaMapper;

    private final UsuarioAutenticado usuarioAutenticado;
    
    public CategoriaGastoResponse ejecutar(
    		String nombre
    		) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();

    	// control de duplicado
    	if (categoriaRepository.existePorNombre(nombre, usuarioId)) {
        	throw new ConflictException("Ya existe una Categoria con ese nombre");
        }
        
    	CategoriaGasto categoria = CategoriaGasto.crear(
    			usuarioId,
    			nombre,
    			TipoObjeto.USUARIO,
    		   	new Fecha(LocalDateTime.now())
        );

    	CategoriaGasto guardada = categoriaRepository.guardar(categoria);
        
        return categoriaMapper.mapToResponse(guardada);
    }
}