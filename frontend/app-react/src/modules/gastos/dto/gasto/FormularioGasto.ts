import type { frecuencia } from "@/modules/gastos/types/typeGastos";
import type { estadoMovimiento } from "@/shared/types/typesApp";

export interface FormularioGasto {
    categoriaGastoId?: number;
    plantillaId?: number | null;
    gastoRecurrenteId?: number | null;
    monto?: number;
    descripcion?: string;
    estado?: estadoMovimiento;

    // Datos de vencimiento
    diaVencimiento?: number;
    mesVencimiento?: number;
    frecuencia?: frecuencia;
    fechaVencimiento?: string | null;
}