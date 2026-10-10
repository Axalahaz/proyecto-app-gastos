import type { estadoMovimiento } from "@/shared/types/typesApp";

export interface GastoResponse {
    id: number;
    categoriaGastoId: number;
    plantillaId: number | null;
    gastoRecurrenteId: number | null;
    monto: number;
    descripcion: string | null;
    estado: estadoMovimiento;
    fechaCreacion: string;
    fechaVencimiento: string | null;
    fechaCambioEstado: string | null;
}