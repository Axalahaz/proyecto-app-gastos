package com.finanzas.app.gastos.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.gastos.application.service.plantillaGasto.EditarPlantillaGastoService;
import com.finanzas.app.gastos.application.service.plantillaGasto.EliminarPlantillaGastoService;
import com.finanzas.app.gastos.application.service.plantillaGasto.ListarPlantillaGastoService;
import com.finanzas.app.gastos.application.service.plantillaGasto.ObtenerPlantillaGastoService;
import com.finanzas.app.gastos.application.service.plantillaGasto.RegistrarPlantillaGastoService;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.EditarPlantillaGastoRequest;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.PlantillaGastoResponse;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.RegistrarPlantillaGastoRequest;
import com.finanzas.app.shared.presentation.filters.TipoObjetoFilter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/plantilla-gastos")
public class PlantillaGastoController {
	
	private RegistrarPlantillaGastoService registrarPlantillaGastoService;
	private EditarPlantillaGastoService editarPlantillaGastoService;
	private EliminarPlantillaGastoService eliminarPlantillaGastoService;
	private ObtenerPlantillaGastoService obtenerPlantillaGastoService;
	private ListarPlantillaGastoService listarPlantillaGastoService;
	
	
	// ----------------------------------------------------
	// REGISTRAR
	
	@PostMapping()
	public ResponseEntity<PlantillaGastoResponse> registrar(
			@Valid @RequestBody RegistrarPlantillaGastoRequest request) {
		
		PlantillaGastoResponse response = registrarPlantillaGastoService.ejecutar(
				request.getCategoriaGastoId(),
				request.getDescripcion()
				);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	// ----------------------------------------------------
	// EDITAR
	
	@PatchMapping("/{id}")
	public ResponseEntity<PlantillaGastoResponse> editar(
			@PathVariable("id") Long plantillaId,
			@Valid @RequestBody EditarPlantillaGastoRequest request) {
		
		PlantillaGastoResponse response = editarPlantillaGastoService.ejecutar(
				plantillaId,
				request.getCategoriaGastoId(),
				request.getDescripcion()
				);
		
		return ResponseEntity.ok(response);
	}

	// ----------------------------------------------------
	// ELIMINAR 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(
			@PathVariable("id") Long plantillaId) {
		
		eliminarPlantillaGastoService.ejecutar(plantillaId);
	
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{id}")
	public ResponseEntity<PlantillaGastoResponse> obtener(
			@PathVariable("id") Long plantillaId) {
		
		PlantillaGastoResponse response = obtenerPlantillaGastoService.ejecutar(plantillaId);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// LISTAR
	
	@GetMapping()
	public ResponseEntity<List<PlantillaGastoResponse>> listarPorEstado(
			@RequestParam(defaultValue = "TODOS") TipoObjetoFilter filtro) {
		
		List<PlantillaGastoResponse> response = listarPlantillaGastoService.ejecutar(filtro);
		
		return ResponseEntity.ok(response);
	}
	
}