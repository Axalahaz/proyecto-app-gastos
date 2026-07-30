import type { frecuencia } from "@/modules/gastos/types/typeGastos";

export interface RegistrarGastoRecurrenteRequest {
    plantillaGastoRecurrenteId?: number;
	descripcion: string;
	diaVencimiento: number;
	mesVencimiento: number | null;
	frecuencia: frecuencia;
}
