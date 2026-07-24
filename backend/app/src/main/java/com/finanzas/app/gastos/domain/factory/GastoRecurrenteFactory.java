package com.finanzas.app.gastos.domain.factory;

import org.springframework.stereotype.Component;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.vo.Periodicidad;
import com.finanzas.app.shared.domain.model.Frecuencia;
import com.finanzas.app.shared.domain.vo.Fecha;

/*
 * Creacion de factory para varios casos de uso
 * */
@Component
public class GastoRecurrenteFactory {

    public GastoRecurrente of(
            String descripcion,
            Frecuencia frecuencia,
            Integer diaVencimiento,
            Integer mesVencimiento,
            boolean periodicidadActiva,
            Fecha fechaCreacion
    ) {

    	Periodicidad periodicidad = null;
    	if(periodicidadActiva) {
    		periodicidad = new Periodicidad(
    				frecuencia,
    				diaVencimiento,
    				mesVencimiento
    				);
    	}

        return GastoRecurrente.crear(
                descripcion,
                periodicidad,
                periodicidadActiva,
                fechaCreacion
        );
    }
}