package com.finanzas.app.gastos.application;

public enum FiltroGastoRecurrente {
    TODOS,
    ACTIVOS,
    INACTIVOS;
    
    public Boolean toEstado() {
        return switch (this) {
            case ACTIVOS -> true;
            case INACTIVOS -> false;
            case TODOS -> null;
        };
    }
}