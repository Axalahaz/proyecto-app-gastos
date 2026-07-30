import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import { Dayjs } from "dayjs";

export interface GastoRecurrenteVencimiento {
    gastoRecurrente: GastoRecurrente;
    actualVencimiento: Dayjs;
    proximoVencimiento: Dayjs;
    diasRestantes: number;
} 