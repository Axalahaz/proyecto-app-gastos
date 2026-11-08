package com.finanzas.app.gastos.gastos.application.service.write;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoApplication;
import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.application.GastoFactory;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.recurrentes.application.contract.ConfGastoRecurrenteApplication;
import com.finanzas.app.gastos.recurrentes.application.contract.ConfGastoRecurrenteData;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.enums.Frecuencia;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

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
public class RegistrarGastoDesdeRecurrenteCommandService {

	private final ConfGastoRecurrenteApplication confGastoRecurrenteApplication;
	private final CategoriaGastoApplication categoriaApplication;

	private final GastoRepository gastoRepository;
    private final GastoApplicationMapper gastoApplicationMapper;
    private final GastoFactory gastoFactory;
    
    private final UsuarioAutenticado usuarioAutenticado;
    
    public GastoResponse ejecutar(
    		BigDecimal importe, 
    		Long confGastoRecurrenteId,
    		LocalDateTime fechaPagoRecurrente
    		) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();

    	// Obtengo datos de la Configuracion de Recurrencia
    	ConfGastoRecurrenteData confGastoRecurrente = confGastoRecurrenteApplication
    			.obtenerDatos(confGastoRecurrenteId, usuarioId);

    	if(confGastoRecurrente.estado().name() == "INACTIVA") {
    		throw new ConflictException(
            		"No se puede operar sobre una recurrencia inactiva");
    	}

    	// Verificar existencia de categoria
    	categoriaApplication.existePorId(confGastoRecurrente.id(), usuarioId);

    	// Comprobar si ya existe un gasto para el periodo
    	LocalDate fechaAhora = LocalDate.now();
    	LocalDateTime fechaInicio;
    	LocalDateTime fechaFin;
    	Frecuencia frecuencia = confGastoRecurrente.frecuencia();
    	
    	switch (frecuencia) {
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
    			confGastoRecurrente.id(),
    		    fechaInicio,
    			fechaFin,
    			usuarioId
    	);

    	if (existePago) throw new ConflictException(
    		    "El gasto ya fue registrado para este período"
    	);
    	
    	// --------------------------------------
    	// creo el gasto confirmando el pago

        Gasto gasto = gastoFactory.crear(
        		usuarioId,
        		importe,
                confGastoRecurrente.descripcion().value(),
                fechaPagoRecurrente,
                confGastoRecurrente.categoriaGastoId().value(),
                confGastoRecurrente.id()
        );

        Gasto guardado = gastoRepository.guardar(gasto);
        
        log.info("Gasto Recuerrente {} registrado correctamente de GR", gasto.getId());
        
        return gastoApplicationMapper.mapToResponse(guardado);
    }
}