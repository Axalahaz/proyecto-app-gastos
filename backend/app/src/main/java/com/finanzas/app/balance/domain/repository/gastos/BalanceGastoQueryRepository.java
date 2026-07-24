package com.finanzas.app.balance.domain.repository.gastos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.finanzas.app.balance.application.dto.gasto.GastoRecurrentesPagosDto;
import com.finanzas.app.balance.application.dto.gasto.GastoTotalPorCategoriaDto;
import com.finanzas.app.shared.domain.model.EstadoMovimiento;

// READ MODEL

public interface BalanceGastoQueryRepository {

	// ---------------------------------------------------------------
	// TOTAL PAGOS POR FECHA
	
	BigDecimal obtenerTotalPagos(
			LocalDateTime fechaInicio, LocalDateTime fechaFin, 
			List<EstadoMovimiento> estados, List<Boolean> recurrente);
	
	// ---------------------------------------------------------------
	// DETALLE PAGOS RECURRENTES POR FECHA
	
	List<GastoRecurrentesPagosDto> obtenerDetallePagosRealizados(
			LocalDateTime fechaInicio, 
			LocalDateTime fechaFin, List<EstadoMovimiento> estados, List<Boolean> recurrente);
	
	// ---------------------------------------------------------------
	// TOTAL GASTOS POR FECHA
	
	BigDecimal obtenerTotal(
			LocalDateTime fechaInicio, 
			LocalDateTime fechaFin, List<EstadoMovimiento> estados);
   
	// ---------------------------------------------------------------
	// TOTAL GASTOS POR CATEGORIA
	
	List<GastoTotalPorCategoriaDto> obtenerTotalPorCategoria(
			LocalDateTime fechaInicio, 
			LocalDateTime fechaFin, List<EstadoMovimiento> estados);
	
}