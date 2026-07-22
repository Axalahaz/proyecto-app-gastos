package com.finanzas.app.gastos.presentation.user.dto.categoriaGasto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoriasResponse {
	private List<CategoriaGastoResponse> lista;

	// ----------------------------------
    // OF
    
	public static CategoriasResponse of(
            List<CategoriaGastoResponse> lista
    ) {
        return new CategoriasResponse(lista);
    }
}
