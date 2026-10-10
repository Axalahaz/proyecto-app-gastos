import dayjs from "dayjs";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { GastoRecurrenteResponse } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteResponse";

export const mapGastoRecurrente = (
    response: GastoRecurrenteResponse
): GastoRecurrente => {
    const fechaCambioEstado = response.fechaCambioEstado === null 
        ? null : dayjs(response.fechaCambioEstado);

    return {
        ...response,
    fechaCreacion: dayjs(response.fechaCreacion),
    fechaCambioEstado,
    }
};
