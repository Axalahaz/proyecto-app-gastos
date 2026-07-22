package com.finanzas.app.gastos.presentation.admin.controller;

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
@RequestMapping("/admin/plantilla-categoria-gasto")
public class PlantillaCategoriaGastoController {

	private final ObtenerCategoriaService obtenerPlantilla;
	private final ListarCategoriasService listarPlantillas;

	// ----------------------------------------------------
	// OBTENER
	
	@GetMapping("/{categoriaId}")
	public ResponseEntity<CategoriaResponse> ObtenerCategoria(@PathVariable Long plantillaId){
		
		CategoriaResponse response = obtenerPlantilla.ejecutar(plantillaId);
		return ResponseEntity.ok(response);
	}

	// ----------------------------------------------------
	// LISTAR TODAS
	@GetMapping()
	public ResponseEntity<List<CategoriaResponse>> ListarCategorias(){
		
		List<CategoriaResponse> response = listarPlantillas.ejecutar();
		
		return ResponseEntity.ok(response);
	}

}
