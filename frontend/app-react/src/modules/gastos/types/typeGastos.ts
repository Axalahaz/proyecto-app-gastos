
// ----------------------------
// GASTO RECURRENTE
export const FRECUENCIA = {
    MENSUAL: "Mensual",
    ANUAL: "Anual",
} as const;

export type frecuencia =
    typeof FRECUENCIA[keyof typeof FRECUENCIA];


export type estadoActividadGastoRecurrente = "ACTIVOS" | "INACTIVOS";

export type estadoGastoRecurrenteFilter = estadoActividadGastoRecurrente | "TODOS";

export const ESTADO_FECHA_GASTO_RECORRENTE = {
    PROGRAMADO: "programado",
    POR_VENCER: "porVencer",
    VENCIDO: "vencido",
    PAGADO: "pagado",
} as const;

export type estadoFechaGastoRecurrente =
    typeof ESTADO_FECHA_GASTO_RECORRENTE[keyof typeof ESTADO_FECHA_GASTO_RECORRENTE];

// -------------------
// MESES
export const MES = [
    { value: 1, label: "Enero" },
    { value: 2, label: "Febrero" },
    { value: 3, label: "Marzo" },
    { value: 4, label: "Abril" },
    { value: 5, label: "Mayo" },
    { value: 6, label: "Junio" },
    { value: 7, label: "Julio" },
    { value: 8, label: "Agosto" },
    { value: 9, label: "Septiembre" },
    { value: 10, label: "Octubre" },
    { value: 11, label: "Noviembre" },
    { value: 12, label: "Diciembre" },
];