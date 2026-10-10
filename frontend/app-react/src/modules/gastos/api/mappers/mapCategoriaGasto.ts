import dayjs from "dayjs";
import type { CategoriaGastoResponse } from "@/modules/gastos/dto/categoria/CategoriaGastoResponse";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";

export const mapCategoriaGasto = (
    response: CategoriaGastoResponse
): CategoriaGasto => ({
    ...response,
    fechaCreacion: dayjs(response.fechaCreacion),
});
