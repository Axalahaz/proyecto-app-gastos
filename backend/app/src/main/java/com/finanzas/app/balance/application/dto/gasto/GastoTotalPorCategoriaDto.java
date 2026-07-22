package com.finanzas.app.balance.application.dto.gasto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GastoTotalPorCategoriaDto {
	private String categoria;
	private BigDecimal total;
	
	// ---------------------------------
	// OF
	
	public static GastoTotalPorCategoriaDto of(String categoria, BigDecimal total) {
		return new GastoTotalPorCategoriaDto(categoria, total);
	}
}
