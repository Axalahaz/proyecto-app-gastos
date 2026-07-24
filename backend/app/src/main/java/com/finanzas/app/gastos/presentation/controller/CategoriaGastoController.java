package com.finanzas.app.gastos.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.app.gastos.application.service.categoriaGasto.EliminarCategoriaGastoService;
import com.finanzas.app.gastos.application.service.categoriaGasto.ListarCategoriasGastosService;
import com.finanzas.app.gastos.application.service.categoriaGasto.ObtenerCategoriaGastoService;
import com.finanzas.app.gastos.application.service.categoriaGasto.RegistrarCategoriaGastoService;
import com.finanzas.app.gastos.presentation.dto.categoriaGasto.RegistrarCategoriaGastoRequest;
import com.finanzas.app.shared.dto.context.CategoriaResponse;
import com.finanzas.app.shared.presentation.filters.FilterOrigen;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/categoria-gasto")
public class CategoriaGastoController {

	private final RegistrarCategoriaGastoService registrarCategoriaService;
	private final EliminarCategoriaGastoService eliminarCategoriaService;
	private final ObtenerCategoriaGastoService obtenerCategoriaService;
	private final ListarCategoriasGastosService listarCategoriasService;

	
	// ----------------------------------------------------
	// CREAR
	
	@PostMapping()
	public ResponseEntity<CategoriaResponse> Crear(
			@Valid @RequestBody RegistrarCategoriaGastoRequest request){
		
		CategoriaResponse response = registrarCategoriaService.ejecutar(
				request.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	// ----------------------------------------------------
	// ELIMINAR
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Eliminar(
			@PathVariable("id") Long categoriaId){
		
		eliminarCategoriaService.ejecutar(categoriaId);
		return ResponseEntity.noContent().build();
	}

	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponse> Obtener(
			@PathVariable("id") Long categoriaId){
		
		CategoriaResponse response = obtenerCategoriaService.ejecutar(categoriaId);
		return ResponseEntity.ok(response);
	}

	// ----------------------------------------------------
	// LISTAR POR FILTRO
	
	@GetMapping()
	public ResponseEntity<List<CategoriaResponse>> Listar(
			@RequestParam(defaultValue = "TODOS") FilterOrigen filter){
		
		List<CategoriaResponse> response = listarCategoriasService.ejecutar(filter);
		return ResponseEntity.ok(response);
	}

}
