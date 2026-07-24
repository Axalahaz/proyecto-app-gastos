package com.finanzas.app.gastos.application.service.categoriaGasto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.dto.context.CategoriaResponse;
import com.finanzas.app.shared.exception.extend.ConflictException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarCategoriaGastoService {

    private final CategoriaGastoRepository categoriaRepository;
    private final CategoriaGastoApplicationMapper categoriaMapper;

    public CategoriaResponse ejecutar(String nombre) {

    	boolean	existe = categoriaRepository.existePorNombre(nombre);
    	
    	if (existe) {
        	throw new ConflictException("Categoria de Gasto");
        }
        
    	CategoriaGasto categoria = CategoriaGasto.crear(
    			nombre,
    			TipoObjeto.USUARIO,
    		   	new Fecha(LocalDateTime.now())
        );

    	CategoriaGasto guardada = categoriaRepository.guardar(categoria);
        
        log.info("Categoria Gasto registrado correctamente");
        
        return categoriaMapper.mapToResponse(guardada);
    }
}