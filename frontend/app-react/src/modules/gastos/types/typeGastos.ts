// ----------------------------
// APP
export type tipoObjeto = "SISTEMA" | "USUARIO";

// ----------------------------
// MOVIMIENTO
export type estadoMovimiento = "ACTIVO" | "ANULADO";

// ----------------------------
// GASTO RECURRENTE
export const FRECUENCIA = {
    MENSUAL: "MENSUAL",
    ANUAL: "ANUAL",
} as const;

export type frecuencia =
    typeof FRECUENCIA[keyof typeof FRECUENCIA];


export type estadoActividadGastoRecurrente = "ACTIVOS" | "INACTIVOS";


export const ESTADO_FECHA_GASTO_RECORRENTE = {
    PROGRAMADO: "programado",
    POR_VENCER: "porVencer",
    VENCIDO: "vencido",
    PAGADO: "pagado",
} as const;

export type estadoFechaGastoRecurrente =
    typeof ESTADO_FECHA_GASTO_RECORRENTE[keyof typeof ESTADO_FECHA_GASTO_RECORRENTE];
