package com.finanzas.app.gastos.categorias.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
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
public class RegistrarCategoriaGastoCommandService {

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