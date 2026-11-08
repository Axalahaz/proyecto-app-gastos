package com.finanzas.app.gastos.gastos.application.service.write;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoApplication;
import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.application.GastoFactory;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 *  -------------------------------
 *  Registro de gasto comun desde una categoria
 *  -------------------------------
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarGastoCommandService {

	private final CategoriaGastoApplication categoriaApplication;
	
    private final GastoRepository gastoRepository;
    private final GastoApplicationMapper gastoApplicationMapper;
    private final GastoFactory gastoFactory;
    
    private final UsuarioAutenticado usuarioAutenticado;
    
    public GastoResponse ejecutar(
    		BigDecimal importe, 
    		String descripcion, 
    		Long categoriaGastoId
    		) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
    	// Verificar existencia de categoria
    	categoriaApplication.existePorId(categoriaGastoId, usuarioId);
    	
        Gasto gasto = gastoFactory.crear(
        		usuarioId,
        		importe,
        		descripcion,
        		null,
        		categoriaGastoId,
                null
        );

        Gasto guardado = gastoRepository.guardar(gasto);
        
        log.info("Gasto {} registrado correctamente", gasto.getId());
        
        return gastoApplicationMapper.mapToResponse(guardado);
    }
}