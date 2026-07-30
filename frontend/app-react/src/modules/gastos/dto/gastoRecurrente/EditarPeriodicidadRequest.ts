import type { frecuencia } from "@/modules/gastos/types/typeGastos";

export interface EditarPeriodicidadRequest {
    diaVencimiento?: number;
    mesVencimiento?: number;
    frecuencia?: frecuencia;
}
