package com.finanzas.app.gastos.presentation.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.gastos.application.service.plantillaGastoRecurrente.ActivarPlantillaGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.plantillaGastoRecurrente.DesactivarPlantillaGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.plantillaGastoRecurrente.EditarPlantillaGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.plantillaGastoRecurrente.EliminarPlantillaGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.plantillaGastoRecurrente.ListarPlantillaGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.plantillaGastoRecurrente.ObtenerPlantillaGastoRecurrenteService;
import com.finanzas.app.gastos.application.service.plantillaGastoRecurrente.RegistrarPlantillaGastoRecurrenteService;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.ActualizarPlantillaGastoRecurrenteRequest;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.PlantillaGastoRecurrenteResponse;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.RegistrarPlantillaGastoRecurrenteRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/admin/plantilla-gastos-recurrentes")
public class PlantillaGastoRecurrenteController {
	
	private final ActivarPlantillaGastoRecurrenteService activarPlantilla;
	private final DesactivarPlantillaGastoRecurrenteService desactivarPlantilla;
	private final EditarPlantillaGastoRecurrenteService editarPlantilla;
	private final EliminarPlantillaGastoRecurrenteService eliminarPlantilla;
	private final ObtenerPlantillaGastoRecurrenteService obtenerPlantilla;
	private final ListarPlantillaGastoRecurrenteService listarPlantilla;
	private final RegistrarPlantillaGastoRecurrenteService registrarPlantilla;

	
	// ----------------------------------------------------
	// REGISTRAR
	
	@PostMapping()
	public ResponseEntity<PlantillaGastoRecurrenteResponse> registrar(
			@Valid @RequestBody RegistrarPlantillaGastoRecurrenteRequest request) {
		
		PlantillaGastoRecurrenteResponse response = registrarPlantilla.ejecutar(
				request.getDescripcion(),
				request.isActivo()
				);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	// ----------------------------------------------------
	// EDITAR
	
	@PatchMapping("/{id}")
	public ResponseEntity<PlantillaGastoRecurrenteResponse> editarBasico(
			@PathVariable("id") Long plantillaId,
			@Valid @RequestBody ActualizarPlantillaGastoRecurrenteRequest request) {
		
		PlantillaGastoRecurrenteResponse response = editarPlantilla.ejecutar(
				plantillaId,
				request.getDescripcion()
				);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// ELIMINAR 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(
			@PathVariable("id") Long plantillaId) {
		
		eliminarPlantilla.ejecutar(plantillaId);
	
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// ESTADO
	
	// ACTIVAR
	
	@PatchMapping("/{id}/activar")
	public ResponseEntity<Void> activar(
			@PathVariable("id") Long plantillaId) {
		
		activarPlantilla.ejecutar(plantillaId);
		
		return ResponseEntity.noContent().build();
	}

	// DESACTIVAR
	
	@PatchMapping("/{id}/desactivar")
	public ResponseEntity<Void> desactivar(
			@PathVariable("id") Long plantillaId) {
		
		desactivarPlantilla.ejecutar(plantillaId);
		
		return ResponseEntity.noContent().build();
	}
	
	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{id}")
	public ResponseEntity<PlantillaGastoRecurrenteResponse> obtener(
			@PathVariable("id") Long plantillaId) {
		
		PlantillaGastoRecurrenteResponse response = obtenerPlantilla.ejecutar(plantillaId);
		
		return ResponseEntity.ok(response);
	}
	
	// ----------------------------------------------------
	// LISTAR
	
	@GetMapping()
	public ResponseEntity<List<PlantillaGastoRecurrenteResponse>> listar() {
		
		List<PlantillaGastoRecurrenteResponse> response = listarPlantilla.ejecutar();
		
		return ResponseEntity.ok(response);
	}
	
}