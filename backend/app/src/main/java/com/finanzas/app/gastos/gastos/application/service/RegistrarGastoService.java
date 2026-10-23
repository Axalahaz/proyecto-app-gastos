package com.finanzas.app.gastos.gastos.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.queryService.PlantillaGastoQueryService;
import com.finanzas.app.gastos.categorias.application.CategoriaGastoQueryService;
import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;

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
public class RegistrarGastoService {

	private final PlantillaGastoQueryService plantillaGastoQueryService;
	private final CategoriaGastoQueryService categoriaQueryService;
    private final GastoRepository gastoRepository;
    private final GastoApplicationMapper gastoApplicationMapper;

    
    public GastoResponse ejecutar(
    		BigDecimal monto, 
    		String descripcion, 
    		Long categoriaGastoId,
    		Long plantillaId
    		) {
    	
    	// control de consistencia
    	categoriaQueryService.existePorId(categoriaGastoId);
        
    	if(plantillaId != null) {
    		plantillaGastoQueryService.existePorId(plantillaId);
    	}
    	
        Money money = new Money(monto);
        Fecha fechaCreacion = new Fecha(LocalDateTime.now());

        Gasto gasto = Gasto.crear(
        		categoriaGastoId,
        		plantillaId,
        		null,
                money,
                descripcion,
                fechaCreacion,
                null
        );

        Gasto guardado = gastoRepository.guardar(gasto);
        
        log.info("Gasto {} registrado correctamente", gasto.getId());
        
        return gastoApplicationMapper.mapToResponse(guardado);
    }
}