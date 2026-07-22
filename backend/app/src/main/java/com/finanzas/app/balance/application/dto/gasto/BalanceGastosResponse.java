package com.finanzas.app.balance.application.dto.gasto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BalanceGastosResponse  {
	
	private BigDecimal total;
	private List<GastoTotalPorCategoriaDto> porCategoria;
	
	// ---------------------------------
	// OF
	
	public static BalanceGastosResponse of(BigDecimal total, List<GastoTotalPorCategoriaDto> porCategoria) {
		return new BalanceGastosResponse (total, porCategoria);
	}
}
