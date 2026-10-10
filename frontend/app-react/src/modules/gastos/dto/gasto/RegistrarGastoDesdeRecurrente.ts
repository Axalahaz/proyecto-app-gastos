import { Dayjs } from "dayjs";

export interface RegistrarGastoDesdeRecurrente {
	monto: number;
	gastoRecurrenteId: number; 
	fechaVencimiento: Dayjs;
}