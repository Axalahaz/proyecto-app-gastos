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
    		Long plantillaId,
            String descripcion,
            Frecuencia frecuencia,
            Integer diaVencimiento,
            Integer mesVencimiento,
            Fecha fechaCreacion
    ) {

    	Periodicidad periodicidad =  new Periodicidad(
    				frecuencia,
    				diaVencimiento,
    				mesVencimiento
    				);

        return GastoRecurrente.crear(
        		plantillaId,
                descripcion,
                periodicidad,
                fechaCreacion
        );
    }
}