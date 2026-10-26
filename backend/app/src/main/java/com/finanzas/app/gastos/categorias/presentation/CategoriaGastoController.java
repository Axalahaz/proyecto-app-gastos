package com.finanzas.app.gastos.categorias.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.app.gastos.categorias.application.service.read.ObtenerCategoriaGastoQueryService;
import com.finanzas.app.gastos.categorias.application.service.write.EditarCategoriaGastoCommandService;
import com.finanzas.app.gastos.categorias.application.service.write.EliminarCategoriaGastoCommandService;
import com.finanzas.app.gastos.categorias.application.service.write.RegistrarCategoriaGastoCommandService;
import com.finanzas.app.gastos.categorias.presentation.dto.CategoriaGastoResponse;
import com.finanzas.app.gastos.categorias.presentation.dto.EditarCategoriaGastoRequest;
import com.finanzas.app.gastos.categorias.presentation.dto.RegistrarCategoriaGastoRequest;
import com.finanzas.app.shared.presentation.filters.TipoObjetoFilter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/categoria-gasto")
public class CategoriaGastoController {

	private final RegistrarCategoriaGastoCommandService registrarCategoriaService;
	private final EditarCategoriaGastoCommandService editarCategoriaService;
	private final EliminarCategoriaGastoCommandService eliminarCategoriaService;
	private final ObtenerCategoriaGastoQueryService obtenerCategoriaService;
	
	// ----------------------------------------------------
	// CREAR
	
	@PostMapping()
	public ResponseEntity<CategoriaGastoResponse> Crear(
			@Valid @RequestBody RegistrarCategoriaGastoRequest request){
		
		CategoriaGastoResponse response = registrarCategoriaService.ejecutar(
				request.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	// ----------------------------------------------------
	// EDITAR
	
	@PatchMapping("/{id}")
	public ResponseEntity<CategoriaGastoResponse> editar(
			@PathVariable("id") Long categoriaId,
			@Valid @RequestBody EditarCategoriaGastoRequest request) {
		
		CategoriaGastoResponse response = editarCategoriaService.ejecutar(
				categoriaId,
				request.getNombre()
				);
		
		return ResponseEntity.ok(response);
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
	public ResponseEntity<CategoriaGastoResponse> Obtener(
			@PathVariable("id") Long categoriaId){
		
		CategoriaGastoResponse response = obtenerCategoriaService.ejecutar(categoriaId);
		return ResponseEntity.ok(response);
	}
}
