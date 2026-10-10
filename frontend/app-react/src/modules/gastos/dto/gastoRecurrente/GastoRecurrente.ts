import type { frecuencia } from "@/modules/gastos/types/typeGastos";
import { Dayjs } from "dayjs";

export interface GastoRecurrente {
	id: number;
	categoriaGastoId: number;
	plantillaId: number | null;
	descripcion: string;
	diaVencimiento: number;
	mesVencimiento: number | null;
	frecuencia: frecuencia;
	activo: boolean;
	fechaCreacion: Dayjs;
	fechaCambioEstado: Dayjs | null;
}