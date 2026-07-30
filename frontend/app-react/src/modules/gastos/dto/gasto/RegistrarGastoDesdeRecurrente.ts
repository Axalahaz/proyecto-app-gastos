import { Dayjs } from "dayjs";

export interface RegistrarGastoDesdeRecurrente {
	monto: number;
	gastoRecurrenteId: number; 
	categoriaGastoId: number;
	fechaVencimiento: Dayjs;
}