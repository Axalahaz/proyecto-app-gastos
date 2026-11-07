package com.finanzas.app.gastos.recurrentes.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.gastos.recurrentes.application.service.read.ListarTodasConfGastosRecurrentesService;
import com.finanzas.app.gastos.recurrentes.application.service.read.ObtenerConfGastoRecurrenteService;
import com.finanzas.app.gastos.recurrentes.application.service.write.ActivarConfGastoRecurrenteService;
import com.finanzas.app.gastos.recurrentes.application.service.write.DesactivarConfGastoRecurrenteService;
import com.finanzas.app.gastos.recurrentes.application.service.write.EditarConfGastoRecurrenteBasicoService;
import com.finanzas.app.gastos.recurrentes.application.service.write.EditarConfGastoRecurrenteConPeriodicidadService;
import com.finanzas.app.gastos.recurrentes.application.service.write.EliminarConfGastoRecurrenteService;
import com.finanzas.app.gastos.recurrentes.application.service.write.RegistrarConfGastoRecurrenteService;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;
import com.finanzas.app.gastos.recurrentes.presentation.dto.EditarConfGastoRecurrenteSinPeriodicidadRequest;
import com.finanzas.app.gastos.recurrentes.presentation.dto.RegistrarConfGastoRecurrenteRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/gasto-recurrente")
public class ConfGastoRecurrenteController {
	
	private final ActivarConfGastoRecurrenteService activarConfGastoRecurrenteService;
	private final DesactivarConfGastoRecurrenteService desactivarConfGastoRecurrenteService;
	private final EditarConfGastoRecurrenteBasicoService editarConfGastoRecurrenteBasicoService;
	private final EditarConfGastoRecurrenteConPeriodicidadService editarConfGastoRecurrenteConPeriodicidadService;
	private final EliminarConfGastoRecurrenteService eliminarConfGastoRecurrenteService;
	private final ObtenerConfGastoRecurrenteService obtenerConfGastoRecurrenteService;
	private final ListarTodasConfGastosRecurrentesService listarTodasConfGastosRecurrentesService;
	
	private final RegistrarConfGastoRecurrenteService registrarConfGastoRecurrenteService;
	
	// ----------------------------------------------------
	// REGISTRAR
	
	@PostMapping()
	public ResponseEntity<ConfGastoRecurrenteResponse> registrar(
			@Valid @RequestBody RegistrarConfGastoRecurrenteRequest request) {
		
		ConfGastoRecurrenteResponse response = registrarConfGastoRecurrenteService.ejecutar(
				request.categoriaGastoId(),
				request.descripcion(),
				request.diaVencimiento(),
				request.mesVencimiento(),
				request.frecuencia()
		);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	// ----------------------------------------------------
	// EDITAR
	
	@PatchMapping("/{id}/basico")
	public ResponseEntity<ConfGastoRecurrenteResponse> editarBasico(
			@PathVariable("id") Long gastoRecurrenteId,
			@Valid @RequestBody EditarConfGastoRecurrenteSinPeriodicidadRequest request) {
		
		ConfGastoRecurrenteResponse response = editarConfGastoRecurrenteBasicoService.ejecutar(
				gastoRecurrenteId,
				request.descripcion()
				);
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{id}/periodicidad")
	public ResponseEntity<ConfGastoRecurrenteResponse> editarConPeriodicidad(
			@PathVariable("id") Long gastoRecurrenteId,
			@Valid @RequestBody com.finanzas.app.gastos.recurrentes.presentation.dto.EditarPeriodicidadRequest request) {
		
		ConfGastoRecurrenteResponse response = editarConfGastoRecurrenteConPeriodicidadService.ejecutar(
				gastoRecurrenteId,
				request.diaVencimiento(),
				request.mesVencimiento(),
				request.frecuencia()
				);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// ESTADO BASICO
	
	// ACTIVAR
	
	@PatchMapping("/{id}/activar")
	public ResponseEntity<Void> activar(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		activarConfGastoRecurrenteService.ejecutar(gastoRecurrenteId);
		
		return ResponseEntity.noContent().build();
	}

	// DESACTIVAR
	
	@PatchMapping("/{id}/desactivar")
	public ResponseEntity<Void> desactivar(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		desactivarConfGastoRecurrenteService.ejecutar(gastoRecurrenteId);
		
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// ELIMINAR 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		eliminarConfGastoRecurrenteService.ejecutar(gastoRecurrenteId);
	
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{id}")
	public ResponseEntity<ConfGastoRecurrenteResponse> obtener(
			@PathVariable("id") Long gastoRecurrenteId) {
		
		ConfGastoRecurrenteResponse response = obtenerConfGastoRecurrenteService.ejecutar(gastoRecurrenteId);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// LISTAR
	
	@GetMapping()
	public ResponseEntity<List<ConfGastoRecurrenteResponse>> listarTodas() {
		
		List<ConfGastoRecurrenteResponse> response = listarTodasConfGastosRecurrentesService.ejecutar();
		
		return ResponseEntity.ok(response);
	}
	
}