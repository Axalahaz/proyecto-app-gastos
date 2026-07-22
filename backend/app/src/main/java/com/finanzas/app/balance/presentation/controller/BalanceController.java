package com.finanzas.app.balance.presentation.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.app.balance.application.dto.gasto.BalanceGastosRecurrentesResponse;
import com.finanzas.app.balance.application.dto.gasto.BalanceGastosResponse;
import com.finanzas.app.balance.application.filtrosEnum.FiltroEstadoMovimiento;
import com.finanzas.app.balance.application.filtrosEnum.FiltroEstadoRecurrente;
import com.finanzas.app.balance.application.filtrosEnum.FiltroFrecuenciaGastoRecurrente;
import com.finanzas.app.balance.application.filtrosEnum.FiltroTipoFechaBalance;
import com.finanzas.app.balance.application.service.ObtenerBalanceGastoRecurrenteService;
import com.finanzas.app.balance.application.service.ObtenerBalanceGastoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/balance")
public class BalanceController {

	private final ObtenerBalanceGastoService balanceGastoService;
	private final ObtenerBalanceGastoRecurrenteService balanceGastoRecurrenteService;
	
    // ----------------------------------------------------
    // OBTENER BALANCE GASTOS
	// futuro agregar ingresos y ahorros
    
	 @GetMapping("/gastos")
	 public ResponseEntity<BalanceGastosResponse> obtenerBalanceGasto(
	 		@RequestParam LocalDate fecha,
	 		@RequestParam(defaultValue = "MENSUAL") FiltroTipoFechaBalance filtroFecha,
	 		@RequestParam(defaultValue = "ACTIVOS") FiltroEstadoMovimiento filtroEstado
	 		) {
		 BalanceGastosResponse balance = balanceGastoService.ejecutar(fecha, filtroFecha, filtroEstado);
		 
		 return ResponseEntity.ok(balance);
	 }

	 // ----------------------------------------------------
	 // OBTENER BALANCE GASTOS FIJOS
	 
	 @GetMapping("/gastos-recurrentes")
	 public ResponseEntity<BalanceGastosRecurrentesResponse> obtenerBalanceGastoRecurrente(
			 @RequestParam LocalDate fecha,
			 @RequestParam(defaultValue = "MENSUAL") FiltroFrecuenciaGastoRecurrente filtroFecha,
			 @RequestParam(defaultValue = "ACTIVOS") FiltroEstadoMovimiento filtroEstado,
			 @RequestParam(defaultValue = "ACTIVOS") FiltroEstadoRecurrente filtroRecurrente
			 ) {
		 BalanceGastosRecurrentesResponse balance = balanceGastoRecurrenteService.ejecutar(fecha, filtroFecha, filtroEstado, filtroRecurrente);
		 
		 return ResponseEntity.ok(balance);
	 }
	 
	 
	 
}
