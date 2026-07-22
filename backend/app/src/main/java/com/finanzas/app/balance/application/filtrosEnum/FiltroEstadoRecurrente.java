package com.finanzas.app.balance.application.filtrosEnum;

import java.util.List;

public enum FiltroEstadoRecurrente {
	
	ACTIVOS {
        @Override
        public List<Boolean> obtenerEstados() {
            return List.of(Boolean.TRUE);
        }
    },

    INACTIVOS {
        @Override
        public List<Boolean> obtenerEstados() {
            return List.of(Boolean.FALSE);
        }
    },

    TODOS {
        @Override
        public List<Boolean> obtenerEstados() {
            return List.of(
            		Boolean.TRUE,
            		Boolean.FALSE
            );
        }
    };

	public abstract List<Boolean> obtenerEstados();
}