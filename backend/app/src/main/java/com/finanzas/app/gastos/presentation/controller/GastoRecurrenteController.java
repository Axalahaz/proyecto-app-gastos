package com.finanzas.app.gastos.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.gastos.application.FiltroGastoRecurrente;
import com.finanzas.app.gastos.application.service.gastoRecurrente.ActivarGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.AgregarPeriodicidadService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.DesactivarGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.EditarGastoRecurrenteBasicoService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.EditarGastoRecurrenteConPeriodicidadService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.EliminarGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.ListarGastosRecurrentesService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.ObtenerGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.QuitarPeriodicidadService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.RegistrarGastoRecurrenteDesdePlantillaService;
import com.finanzas.app.gastos.application.service.gastoRecurrente.RegistrarGastoRecurrenteService;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.ActualizarGastoRecurrenteSinPeriodicidadRequest;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.ActualizarPeriodicidadRequest;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.RegistrarGastoRecurrenteConPlantillaRequest;
import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.RegistrarGastoRecurrenteSinPlantillaRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/gastos-recurrentes")
public class GastoRecurrenteController {
	
	private final QuitarPeriodicidadService quitarPeriodicidadService;
	private final AgregarPeriodicidadService agregarPeriodicidadService;
	private final ActivarGastoRecurrenteService activarGastoRecurrenteService;
	private final DesactivarGastoRecurrenteService desactivarGastoRecurrenteService;
	private final EditarGastoRecurrenteBasicoService editarGastoRecurrenteBasicoService;
	private final EditarGastoRecurrenteConPeriodicidadService editarGastoRecurrenteConPeriodicidadService;
	private final EliminarGastoRecurrenteService eliminarGastoRecurrenteService;
	private final ListarGastosRecurrentesService listarGastosRecurrentesService;
	private final ObtenerGastoRecurrenteService obtenerGastoRecurrenteService;
	private final RegistrarGastoRecurrenteDesdePlantillaService registrarGastoRecurrenteDesdePlantillaService;
	private final RegistrarGastoRecurrenteService registrarGastoRecurrenteService;

	
	// ----------------------------------------------------
	// REGISTRAR
	
	@PostMapping()
	public ResponseEntity<GastoRecurrenteResponse> registrarDesdeCero(
			@Valid @RequestBody RegistrarGastoRecurrenteSinPlantillaRequest request) {
		
		GastoRecurrenteResponse response = registrarGastoRecurrenteService.ejecutar(
				request.getDescripcion(),
				request.getDiaVencimiento(),
				request.getMesVencimiento(),
				request.getFrecuencia()
				);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}

	@PostMapping()
	public ResponseEntity<GastoRecurrenteResponse> registrarDesdePlantilla(
			@Valid @RequestBody RegistrarGastoRecurrenteConPlantillaRequest request) {
		
		GastoRecurrenteResponse response = registrarGastoRecurrenteDesdePlantillaService.ejecutar(
				request.getPlantillaGastoRecurrenteId(),
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
			@Valid @RequestBody ActualizarGastoRecurrenteSinPeriodicidadRequest request) {
		
		GastoRecurrenteResponse response = editarGastoRecurrenteBasicoService.ejecutar(
				gastoRecurrenteId,
				request.getDescripcion()
				);
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{id}/periodicidad")
	public ResponseEntity<GastoRecurrenteResponse> editarConPeriodicidad(
			@PathVariable("id") Long gastoRecurrenteId,
			@Valid @RequestBody ActualizarPeriodicidadRequest request) {
		
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
	// ESTADO PERIODICIDAD
	
	// AGREGAR
	
	@PatchMapping("/{id}/agregar-periodicidad")
	public ResponseEntity<Void> agregarPeriodicidad(
			@PathVariable("id") Long gastoRecurrenteId,
			@Valid @RequestBody ActualizarPeriodicidadRequest request) {
		
		agregarPeriodicidadService.ejecutar(
				gastoRecurrenteId,
				request.getDiaVencimiento(),
				request.getMesVencimiento(),
				request.getFrecuencia()
				);
		
		return ResponseEntity.noContent().build();
	}
	
	// QUITAR
	
	@PatchMapping("/{id}/quitar-periodicidad")
	public ResponseEntity<Void> quitarPeriodicidad(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		quitarPeriodicidadService.ejecutar(gastoRecurrenteId);
		
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