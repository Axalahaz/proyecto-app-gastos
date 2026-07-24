package com.finanzas.app.gastos.application.service.gasto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.application.queryService.GastoRecurrenteQueryService;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.extend.NotFoundException;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 *  -------------------------------
 *  Registro de gasto desde un gasto recurrente
 *  -------------------------------
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarGastoDesdeRecurrenteService {

	private final GastoRecurrenteQueryService gastoRecurrenteQueryService;

	private final CategoriaGastoRepository categoriaRepository;

	private final GastoRepository gastoRepository;
    private final GastoApplicationMapper gastoApplicationMapper;

    
    public GastoResponse ejecutar(
    		BigDecimal monto, 
    		Long gastoRecurrenteId,
    		Long categoriaGastoId
    		) {

    	CategoriaGasto categoria =
    			categoriaRepository.buscarPorId(categoriaGastoId)
    			.orElseThrow(() ->
    			NotFoundException.of("Categoria Gasto", categoriaGastoId));

        GastoRecurrente gastoRecurrente = gastoRecurrenteQueryService.obtenerPorId(gastoRecurrenteId);

    	gastoRecurrente.validarRecurrenciaEstaActiva();
    	
    	// ya esta pago?
    	LocalDate fechaAhora = LocalDate.now();

    	LocalDateTime fechaInicio;
    	LocalDateTime fechaFin;
    	
    	switch (gastoRecurrente.getPeriodicidad().getFrecuencia()) {
	    	case MENSUAL -> {
	    		fechaInicio = fechaAhora.withDayOfMonth(1).atStartOfDay();
	    		fechaFin = fechaAhora.withDayOfMonth(1).plusMonths(1).atStartOfDay();
	    	}
	    	case ANUAL -> {
	    		fechaInicio = fechaAhora.withDayOfYear(1).atStartOfDay();
	    		fechaFin = fechaAhora.withDayOfYear(1).plusYears(1).atStartOfDay();
	    	}
	    	default -> { throw ValidationException.of("Frecuencia invalida"); }
    	}

    	boolean existePago = gastoRepository.existePagoActivoEnPeriodo(
    		    gastoRecurrenteId,
    		    fechaInicio,
    			fechaFin);

    	if (existePago) throw new ConflictException(
    		    "El gasto recurrente ya fue registrado para este período"
    	);
    	
    	// creo el pago/gasto
    	Money money = new Money(monto);
        Fecha fechaCreacion = new Fecha(LocalDateTime.now());

        Gasto gasto = Gasto.crear(
        		categoria.getId(),
        		gastoRecurrente.getId(),
                money,
                gastoRecurrente.getDescripcion(),
                fechaCreacion
        );

        Gasto guardado = gastoRepository.guardar(gasto);
        
        log.info("Gasto {} registrado correctamente de GR", gasto.getId());
        
        return gastoApplicationMapper.mapToResponse(guardado);
    }
}