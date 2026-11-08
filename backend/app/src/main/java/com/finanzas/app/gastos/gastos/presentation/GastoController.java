package com.finanzas.app.gastos.gastos.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.gastos.gastos.application.service.read.ListarGastosTodosQueryService;
import com.finanzas.app.gastos.gastos.application.service.read.ObtenerGastoQueryService;
import com.finanzas.app.gastos.gastos.application.service.write.AnularGastoCommandService;
import com.finanzas.app.gastos.gastos.application.service.write.EditarGastoCommandService;
import com.finanzas.app.gastos.gastos.application.service.write.EliminarGastoCommandService;
import com.finanzas.app.gastos.gastos.application.service.write.RegistrarGastoDesdeRecurrenteCommandService;
import com.finanzas.app.gastos.gastos.application.service.write.RegistrarGastoCommandService;
import com.finanzas.app.gastos.gastos.application.service.write.VolverRecurrenteCommandService;
import com.finanzas.app.gastos.gastos.presentation.dto.EditarGastoRequest;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;
import com.finanzas.app.gastos.gastos.presentation.dto.RegistrarGastoDesdeRecurrenteRequest;
import com.finanzas.app.gastos.gastos.presentation.dto.RegistrarGastoRequest;
import com.finanzas.app.gastos.gastos.presentation.dto.VolverRecurrenteRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/gasto")
public class GastoController {
	
	private final VolverRecurrenteCommandService volverRecurrenteService;
	private final AnularGastoCommandService anularGastoService;
	private final EditarGastoCommandService editarGastoService;
	private final EliminarGastoCommandService eliminarGastoService;
	private final ListarGastosTodosQueryService listarGastosTodosService;
	private final ObtenerGastoQueryService obtenerGastoService;
	private final RegistrarGastoDesdeRecurrenteCommandService registrarGastoDesdeRecurrenteService;
	private final RegistrarGastoCommandService registrarGastoService;

	// ----------------------------------------------------
	// REGISTRAR
	
	@PostMapping()
	public ResponseEntity<GastoResponse> registrar(
			@Valid @RequestBody RegistrarGastoRequest request) {
		
		GastoResponse response = registrarGastoService.ejecutar(
				request.importe(),
				request.descripcion(),
				request.categoriaGastoId()
				);
		
		return ResponseEntity.status(HttpStatus.CREATED)
		        .body(response);
	}
	
	@PostMapping("/recurrente")
	public ResponseEntity<GastoResponse> registrarDesdeRecurrente(
			@Valid @RequestBody RegistrarGastoDesdeRecurrenteRequest request) {
		
		GastoResponse response = registrarGastoDesdeRecurrenteService.ejecutar(
				request.importe(),
				request.confGastoRecurrenteId(),
				request.fechaPagoRecurrente()
				);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	// ----------------------------------------------------
	// EDITAR
	
	@PatchMapping("/{id}")
	public ResponseEntity<GastoResponse> editar(
			@PathVariable("id") Long gastoId,
			@Valid @RequestBody EditarGastoRequest request) {
		
		GastoResponse response = editarGastoService.ejecutar(
				gastoId,
				request.importe(),
				request.descripcion(),
				request.categoriaGastoId()
				);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// VOLVER RECURRENTE

	@PatchMapping("/{id}/volver-recurrente")
	public ResponseEntity<GastoResponse> volverRecurrenteConPeriodicidad(
			@PathVariable("id") Long gastoId,
			@Valid @RequestBody VolverRecurrenteRequest request) {
		
		GastoResponse response = volverRecurrenteService.ejecutar(
				gastoId,
				request.diaVencimiento(),
				request.mesVencimiento(),
				request.frecuencia()
				);
		
		return ResponseEntity.ok(response);
	}

	// ----------------------------------------------------
	// ANULAR
	
	@PatchMapping("/{id}/anular")
	public ResponseEntity<Void> anular(
			@PathVariable("id") Long gastoId) {
		
		anularGastoService.ejecutar(gastoId);
		
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// ELIMINAR
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(
			@PathVariable("id") Long gastoId) {
		
		eliminarGastoService.ejecutar(gastoId);
	
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{id}")
	public ResponseEntity<GastoResponse> obtener(
			@PathVariable("id") Long gastoId) {
		
		GastoResponse response = obtenerGastoService.ejecutar(gastoId);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// LISTAR 
	
	@GetMapping()
	public ResponseEntity<List<GastoResponse>> listarTodos() {

	    List<GastoResponse> response = listarGastosTodosService.ejecutar();

	    return ResponseEntity.ok(response);
	}
	
}