package com.finanzas.app.balance.application.dto.gasto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GastoRecurrentesPagosDto {
	private String descripcion;
	private BigDecimal total;
	private String nombre;
	private LocalDateTime fechaCreacion;
	
	// ---------------------------------
	// OF
	
	public static GastoRecurrentesPagosDto of(String descripcion, BigDecimal total, String nombre, LocalDateTime fechaCreacion) {
		return new GastoRecurrentesPagosDto(descripcion, total, nombre, fechaCreacion);
	}
}
