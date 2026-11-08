package com.finanzas.app.gastos.recurrentes.application;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.shared.domain.enums.Frecuencia;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.UsuarioId;

@Component
public class ConfGastoRecurrenteFactory {

	public ConfGastoRecurrente crear(
			Long usuarioId,
	        String descripcion,
	        Long categoriaGastoId,
	        Frecuencia frecuencia,
	        Integer diaVencimiento,
	        Integer mesVencimiento
	    ) {
		
		UsuarioId usuarioIdVO = new UsuarioId(usuarioId);
		Descripcion descripcionVO = new Descripcion(descripcion);
        CategoriaId categoriaGastoIdVO = new CategoriaId(categoriaGastoId);
        Fecha fechaCreacionVO = new Fecha(LocalDateTime.now());
		
		return ConfGastoRecurrente.crear(
				usuarioIdVO,
				descripcionVO,
				categoriaGastoIdVO,
                frecuencia,
                diaVencimiento,
                mesVencimiento,
                fechaCreacionVO
            );
	}
}
