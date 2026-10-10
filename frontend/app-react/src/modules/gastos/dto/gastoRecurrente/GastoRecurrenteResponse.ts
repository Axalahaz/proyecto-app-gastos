import type { frecuencia } from "@/modules/gastos/types/typeGastos";

export interface GastoRecurrenteResponse {
	id: number;
	categoriaGastoId: number;
	plantillaId: number | null;
	descripcion: string;
	diaVencimiento: number;
	mesVencimiento: number | null;
	frecuencia: frecuencia;
	activo: boolean;
	fechaCreacion: string;
	fechaCambioEstado: string | null;
}