// ----------------------------
// APP
export const TIPO_OBJETO = {
    SISTEMA: "SISTEMA",
    USUARIO: "USUARIO",
} as const;

export type tipoObjeto =
    typeof TIPO_OBJETO[keyof typeof TIPO_OBJETO];

export type tipoObjetoFilter = tipoObjeto | "TODOS";

// ----------------------------
// MOVIMIENTO
export const ESTADO_MOVIMIENTO = {
    ACTIVO: "ACTIVO",
    ANULADO: "ANULADO",
} as const;

export type estadoMovimiento =
    typeof ESTADO_MOVIMIENTO[keyof typeof ESTADO_MOVIMIENTO];


