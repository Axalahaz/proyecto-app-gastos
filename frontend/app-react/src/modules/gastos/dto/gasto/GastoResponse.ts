import type { estadoMovimiento } from "@/modules/gastos/types/typeGastos";

export interface GastoResponse {
    id: number;
    categoriaGastoId: number;
    gastoRecurrenteId: number;
    monto: number;
    descripcion: string | null;
    estadoMovimiento: estadoMovimiento;
    fechaCreacion: string;
    fechaVencimiento: string | null;
    fechaCambioEstado: string | null;
}