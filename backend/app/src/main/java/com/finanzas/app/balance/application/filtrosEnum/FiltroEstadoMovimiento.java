package com.finanzas.app.balance.application.filtrosEnum;

import java.util.List;

import com.finanzas.app.shared.domain.model.EstadoMovimiento;

public enum FiltroEstadoMovimiento {

    ACTIVOS {
        @Override
        public List<EstadoMovimiento> obtenerEstados() {
            return List.of(EstadoMovimiento.ACTIVO);
        }
    },

    ANULADOS {
        @Override
        public List<EstadoMovimiento> obtenerEstados() {
            return List.of(EstadoMovimiento.ANULADO);
        }
    },

    TODOS {
        @Override
        public List<EstadoMovimiento> obtenerEstados() {
            return List.of(
                    EstadoMovimiento.ACTIVO,
                    EstadoMovimiento.ANULADO
            );
        }
    };

	public abstract List<EstadoMovimiento> obtenerEstados();
}