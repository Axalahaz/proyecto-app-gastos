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

import com.finanzas.app.gastos.categorias.application.service.EditarCategoriaGastoCommandService;
import com.finanzas.app.gastos.categorias.application.service.EliminarCategoriaGastoCommandService;
import com.finanzas.app.gastos.categorias.application.service.ListarCategoriasGastosQueryService;
import com.finanzas.app.gastos.categorias.application.service.ObtenerCategoriaGastoQueryService;
import com.finanzas.app.gastos.categorias.application.service.RegistrarCategoriaGastoCommandService;
import com.finanzas.app.gastos.categorias.presentation.dto.EditarCategoriaGastoRequest;
import com.finanzas.app.gastos.categorias.presentation.dto.RegistrarCategoriaGastoRequest;
import com.finanzas.app.shared.dto.context.CategoriaResponse;
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
	private final ListarCategoriasGastosQueryService listarCategoriasGastosService;

	
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
	// EDITAR
	
	@PatchMapping("/{id}")
	public ResponseEntity<CategoriaResponse> editar(
			@PathVariable("id") Long categoriaId,
			@Valid @RequestBody EditarCategoriaGastoRequest request) {
		
		CategoriaResponse response = editarCategoriaService.ejecutar(
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
	public ResponseEntity<CategoriaResponse> Obtener(
			@PathVariable("id") Long categoriaId){
		
		CategoriaResponse response = obtenerCategoriaService.ejecutar(categoriaId);
		return ResponseEntity.ok(response);
	}

	// ----------------------------------------------------
	// LISTAR POR FILTRO
	
	@GetMapping("")
	public ResponseEntity<List<CategoriaResponse>> listar(
			@RequestParam(defaultValue = "TODOS") TipoObjetoFilter filtro){
		
		List<CategoriaResponse> response = listarCategoriasGastosService.ejecutar(filtro);
		return ResponseEntity.ok(response);
	}

}
