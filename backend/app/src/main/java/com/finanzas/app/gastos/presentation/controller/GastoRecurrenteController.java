package com.finanzas.app.gastos.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.gastos.application.FiltroGastoRecurrente;
import com.finanzas.app.gastos.application.service.gastoRecurrente.ActivarGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.DesactivarGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.EditarGastoRecurrenteBasicoService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.EditarGastoRecurrenteConPeriodicidadService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.EliminarGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.ListarGastosRecurrentesService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.ObtenerGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.RegistrarGastoRecurrenteService;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.EditarGastoRecurrenteSinPeriodicidadRequest;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.EditarPeriodicidadRequest;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.RegistrarGastoRecurrenteRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/gasto-recurrente")
public class GastoRecurrenteController {
	
	private final ActivarGastoRecurrenteService activarGastoRecurrenteService;
	private final DesactivarGastoRecurrenteService desactivarGastoRecurrenteService;
	private final EditarGastoRecurrenteBasicoService editarGastoRecurrenteBasicoService;
	private final EditarGastoRecurrenteConPeriodicidadService editarGastoRecurrenteConPeriodicidadService;
	private final EliminarGastoRecurrenteService eliminarGastoRecurrenteService;
	private final ListarGastosRecurrentesService listarGastosRecurrentesService;
	private final ObtenerGastoRecurrenteService obtenerGastoRecurrenteService;
	private final RegistrarGastoRecurrenteService registrarGastoRecurrenteService;

	
	// ----------------------------------------------------
	// REGISTRAR
	
	@PostMapping()
	public ResponseEntity<GastoRecurrenteResponse> registrar(
			@Valid @RequestBody RegistrarGastoRecurrenteRequest request) {
		
		GastoRecurrenteResponse response = registrarGastoRecurrenteService.ejecutar(
				request.getPlantillaGastoRecurrenteId(),
				request.getDescripcion(),
				request.getDiaVencimiento(),
				request.getMesVencimiento(),
				request.getFrecuencia()
				);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	// ----------------------------------------------------
	// EDITAR
	
	@PatchMapping("/{id}/basico")
	public ResponseEntity<GastoRecurrenteResponse> editarBasico(
			@PathVariable("id") Long gastoRecurrenteId,
			@Valid @RequestBody EditarGastoRecurrenteSinPeriodicidadRequest request) {
		
		GastoRecurrenteResponse response = editarGastoRecurrenteBasicoService.ejecutar(
				gastoRecurrenteId,
				request.getDescripcion()
				);
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{id}/periodicidad")
	public ResponseEntity<GastoRecurrenteResponse> editarConPeriodicidad(
			@PathVariable("id") Long gastoRecurrenteId,
			@Valid @RequestBody EditarPeriodicidadRequest request) {
		
		GastoRecurrenteResponse response = editarGastoRecurrenteConPeriodicidadService.ejecutar(
				gastoRecurrenteId,
				request.getDiaVencimiento(),
				request.getMesVencimiento(),
				request.getFrecuencia()
				);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// ESTADO BASICO
	
	// ACTIVAR
	
	@PatchMapping("/{id}/activar")
	public ResponseEntity<Void> activar(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		activarGastoRecurrenteService.ejecutar(gastoRecurrenteId);
		
		return ResponseEntity.noContent().build();
	}

	// DESACTIVAR
	
	@PatchMapping("/{id}/desactivar")
	public ResponseEntity<Void> desactivar(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		desactivarGastoRecurrenteService.ejecutar(gastoRecurrenteId);
		
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// ELIMINAR 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		eliminarGastoRecurrenteService.ejecutar(gastoRecurrenteId);
	
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{id}")
	public ResponseEntity<GastoRecurrenteResponse> obtener(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		GastoRecurrenteResponse response = obtenerGastoRecurrenteService.ejecutar(gastoRecurrenteId);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// LISTAR
	
	@GetMapping()
	public ResponseEntity<List<GastoRecurrenteResponse>> listarPorEstado(
			@RequestParam(defaultValue = "TODOS") FiltroGastoRecurrente filtro) {
		
		List<GastoRecurrenteResponse> response = listarGastosRecurrentesService.ejecutar(filtro);
		
		return ResponseEntity.ok(response);
	}
	
}