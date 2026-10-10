import dayjs from "dayjs";
import type { PlantillaGastoResponse } from "@/modules/gastos/dto/plantillaGasto/PlantillaGastoResponse";
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";

export const mapPlantillaGasto = (
    response: PlantillaGastoResponse
): PlantillaGasto => ({
    ...response,
    fechaCreacion: dayjs(response.fechaCreacion),
});
