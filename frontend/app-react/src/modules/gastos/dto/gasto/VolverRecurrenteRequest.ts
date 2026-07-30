import type { frecuencia } from "@/modules/gastos/types/typeGastos";

export interface VolverRecurrenteRequest {
    diaVencimiento: number;
	mesVencimiento?: number;
    frecuencia: frecuencia;
}