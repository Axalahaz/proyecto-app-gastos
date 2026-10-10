import dayjs from "dayjs";
import type { GastoResponse } from "@/modules/gastos/dto/gasto/GastoResponse";
import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { RegistrarGastoDesdeRecurrente } from "@/modules/gastos/dto/gasto/RegistrarGastoDesdeRecurrente";
import type { RegistrarGastoDesdeRecurrenteRequest } from "@/modules/gastos/dto/gasto/RegistrarGastoDesdeRecurrenteRequest";

export const mapGasto = (
    response: GastoResponse
): Gasto => {
    const fechaVencimiento = response.fechaVencimiento === null 
        ? null : dayjs(response.fechaVencimiento);
    const fechaCambioEstado = response.fechaCambioEstado === null 
        ? null : dayjs(response.fechaCambioEstado);

    return {
        ...response,
    fechaCreacion: dayjs(response.fechaCreacion),
    fechaVencimiento,
    fechaCambioEstado,
    }
};

export const mapRegistrarGasto = (
    response: RegistrarGastoDesdeRecurrente
): RegistrarGastoDesdeRecurrenteRequest => {
    return {
        ...response,
    fechaVencimiento: response.fechaVencimiento.format("YYYY-MM-DDTHH:mm:ss"),
    }
};

