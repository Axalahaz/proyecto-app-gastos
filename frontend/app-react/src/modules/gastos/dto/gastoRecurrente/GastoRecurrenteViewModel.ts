import type { estadoFechaGastoRecurrente } from "@/modules/gastos/types/typeGastos";
import type { GastoRecurrenteVencimiento } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteVencimiento";

export interface GastoRecurrenteViewModel extends GastoRecurrenteVencimiento {
    estado: estadoFechaGastoRecurrente;
} 