import type { frecuencia } from "@/modules/gastos/types/typeGastos";

export interface RegistrarGastoRecurrenteRequest {
    categoriaGastoId: number;
    plantillaGastoRecurrenteId?: number;
	descripcion: string;
	diaVencimiento: number;
	mesVencimiento: number | null;
	frecuencia: frecuencia;
}
