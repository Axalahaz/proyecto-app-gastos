package com.finanzas.app.balance.application.filtrosEnum;

import java.util.List;

import com.finanzas.app.shared.domain.model.Frecuencia;

public enum FiltroFrecuenciaGastoRecurrente {

    MENSUAL {
        @Override
        public List<Frecuencia> obtenerFrecuencias() {
            return List.of(Frecuencia.MENSUAL);
        }
    },

    ANUAL {
        @Override
        public List<Frecuencia> obtenerFrecuencias() {
            return List.of(Frecuencia.ANUAL);
        }
    },

    TODOS {
        @Override
        public List<Frecuencia> obtenerFrecuencias() {
            return List.of(
            		Frecuencia.MENSUAL,
            		Frecuencia.ANUAL
            );
        }
    };

	public abstract List<Frecuencia> obtenerFrecuencias();
}