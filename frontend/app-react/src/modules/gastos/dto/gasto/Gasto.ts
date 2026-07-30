import type { estadoMovimiento } from "@/modules/gastos/types/typeGastos";
import { Dayjs } from "dayjs";

export interface Gasto {
    id: number;
    categoriaGastoId: number;
    gastoRecurrenteId: number;
    monto: number;
    descripcion: string | null;
    estadoMovimiento: estadoMovimiento;
    fechaCreacion: Dayjs;
    fechaVencimiento: Dayjs | null;
    fechaCambioEstado: Dayjs | null;
}