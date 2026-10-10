import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";
import type { tipoObjetoFilter } from "@/shared/types/typesApp";

import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";
import type { PlantillaGastoResponse } from "@/modules/gastos/dto/plantillaGasto/PlantillaGastoResponse";
import { mapPlantillaGasto } from "@/modules/gastos/api/mappers/mapPlantillaGasto";

export const plantillaGastoApiQueryRead = {

    async obtenerPorId(id: number): Promise<PlantillaGasto> {
        const response = await api.get<PlantillaGastoResponse>(
            `${ENDPOINTS.plantillaGasto}/${id}`
        );

        return mapPlantillaGasto(response.data);
    },

    async listar(
        tipoObjetoFilter: tipoObjetoFilter
    ): Promise<PlantillaGasto[]> {
        const response = await api.get<PlantillaGastoResponse[]>(
            ENDPOINTS.plantillaGasto,
            {
                params: { filtro: tipoObjetoFilter },
            }
        );

        return response.data.map(mapPlantillaGasto);
    },
};