package com.finanzas.app.gastos.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.gastos.application.service.gasto.AnularGastoService;
import com.finanzas.app.gastos.application.service.gasto.EditarGastoService;
import com.finanzas.app.gastos.application.service.gasto.EliminarGastoService;
import com.finanzas.app.gastos.application.service.gasto.ListarGastosPorCategoriaService;
import com.finanzas.app.gastos.application.service.gasto.ListarGastosTodosService;
import com.finanzas.app.gastos.application.service.gasto.ObtenerGastoService;
import com.finanzas.app.gastos.application.service.gasto.RegistrarGastoDesdeRecurrenteService;
import com.finanzas.app.gastos.application.service.gasto.RegistrarGastoService;
import com.finanzas.app.gastos.application.service.gasto.VolverRecurrenteService;
import com.finanzas.app.gastos.presentation.dto.gasto.EditarGastoRequest;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;
import com.finanzas.app.gastos.presentation.dto.gasto.RegistrarGastoDesdeRecurrenteRequest;
import com.finanzas.app.gastos.presentation.dto.gasto.RegistrarGastoRequest;
import com.finanzas.app.gastos.presentation.dto.gasto.VolverRecurrenteRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/gasto")
public class GastoController {
	
	private final VolverRecurrenteService volverRecurrenteService;
	private final AnularGastoService anularGastoService;
	private final EditarGastoService editarGastoService;
	private final EliminarGastoService eliminarGastoService;
	private final ListarGastosPorCategoriaService listarGastosPorCategoriaService;
	private final ListarGastosTodosService listarGastosTodosService;
	private final ObtenerGastoService obtenerGastoService;
	private final RegistrarGastoDesdeRecurrenteService registrarGastoDesdeRecurrenteService;
	private final RegistrarGastoService registrarGastoService;

	// ----------------------------------------------------
	// REGISTRAR
	
	@PostMapping()
	public ResponseEntity<GastoResponse> registrar(
			@Valid @RequestBody RegistrarGastoRequest request) {
		
		GastoResponse response = registrarGastoService.ejecutar(
				request.getMonto(),
				request.getDescripcion(),
				request.getCategoriaGastoId(),
				request.getPlantillaId()
				);
		
		return ResponseEntity.status(HttpStatus.CREATED)
		        .body(response);
	}
	
	@PostMapping("/recurrente")
	public ResponseEntity<GastoResponse> registrarDesdeRecurrente(
			@Valid @RequestBody RegistrarGastoDesdeRecurrenteRequest request) {
		
		GastoResponse response = registrarGastoDesdeRecurrenteService.ejecutar(
				request.getMonto(),
				request.getGastoRecurrenteId(),
				request.getCategoriaGastoId()
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
				request.getCategoriaGastoId(),
				request.getMonto(),
				request.getDescripcion()
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
				request.getDiaVencimiento(),
				request.getMesVencimiento(),
				request.getFrecuencia()
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
	// LISTAR TODOS
	
	@GetMapping()
	public ResponseEntity<List<GastoResponse>> listarTodos() {
		
		List<GastoResponse> response = listarGastosTodosService.ejecutar();
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// LISTAR POR CATEGORIA
	
	@GetMapping()
	public ResponseEntity<List<GastoResponse>> listarPorCategoria(
			@RequestParam("categoria") Long categoriaId) {
		
		List<GastoResponse> response = listarGastosPorCategoriaService.ejecutar(categoriaId);
		
		return ResponseEntity.ok(response);
	}
	
}