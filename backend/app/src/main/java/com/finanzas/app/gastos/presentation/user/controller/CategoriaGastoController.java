package com.finanzas.app.gastos.presentation.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.app.gastos.application.service.categoriaGasto.ListarCategoriasService;
import com.finanzas.app.gastos.application.service.categoriaGasto.ObtenerCategoriaService;
import com.finanzas.app.shared.dto.context.CategoriaResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/categoria-gasto")
public class CategoriaGastoController {

	private final ObtenerCategoriaService obtenerCategoriaService;
	private final ListarCategoriasService listarCategoriasService;

	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{categoriaId}")
	public ResponseEntity<CategoriaResponse> ObtenerCategoria(@PathVariable Long categoriaId){
		
		CategoriaResponse response = obtenerCategoriaService.ejecutar(categoriaId);
		return ResponseEntity.ok(response);
	}

	// ----------------------------------------------------
	// LISTAR TODAS
	@GetMapping()
	public ResponseEntity<List<CategoriaResponse>> ListarCategorias(){
		
		List<CategoriaResponse> response = listarCategoriasService.ejecutar();
		return ResponseEntity.ok(response);
	}

}
