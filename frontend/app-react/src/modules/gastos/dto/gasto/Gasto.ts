import type { estadoMovimiento } from "@/shared/types/typesApp";
import { Dayjs } from "dayjs";

export interface Gasto {
    id: number;
    categoriaGastoId: number;
    plantillaId: number | null;
    gastoRecurrenteId: number | null;
    monto: number;
    descripcion: string | null;
    estado: estadoMovimiento;
    fechaCreacion: Dayjs;
    fechaVencimiento: Dayjs | null;
    fechaCambioEstado: Dayjs | null;
}