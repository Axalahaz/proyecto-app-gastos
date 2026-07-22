package com.finanzas.app.balance.application.dto.gasto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BalanceGastosRecurrentesResponse  {
	
	private BigDecimal total;
	private List<GastoRecurrentesPagosDto> porPagos;
	
	// ---------------------------------
	// OF
	
	public static BalanceGastosRecurrentesResponse of(BigDecimal total, List<GastoRecurrentesPagosDto> porPagos) {
		return new BalanceGastosRecurrentesResponse (total, porPagos);
	}
}
