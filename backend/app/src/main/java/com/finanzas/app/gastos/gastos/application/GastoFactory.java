package com.finanzas.app.gastos.gastos.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.domain.vo.UsuarioId;

@Component
public class GastoFactory {
	
	public Gasto crear(
			Long usuarioId,
			BigDecimal importe, 
			String descripcion,
			LocalDateTime fechaPagoRecurrente,
			Long categoriaGastoId,
    	    Long confGastoRecurrenteId
    	    ) {
		
		UsuarioId usuarioIdVO = new UsuarioId(usuarioId);
        Money moneyVO = new Money(importe);
        Descripcion descripcionVO = new Descripcion(descripcion);
        Fecha fechaCreacionVO = new Fecha(LocalDateTime.now());
        Fecha fechaPagoRecurrenteVO = fechaPagoRecurrente != null 
        		? new Fecha(fechaPagoRecurrente)
        		: null;
        CategoriaId categoriaGastoIdVO = new CategoriaId(categoriaGastoId);
        
		return Gasto.crear(
        		usuarioIdVO,
        		moneyVO,
        		descripcionVO,
        		fechaCreacionVO,
        		fechaPagoRecurrenteVO,
        		categoriaGastoIdVO,
        		confGastoRecurrenteId
        );
	}

}
