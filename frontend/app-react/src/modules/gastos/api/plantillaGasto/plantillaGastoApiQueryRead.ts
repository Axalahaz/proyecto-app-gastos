import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";
import type { tipoObjeto } from "@/modules/gastos/types/typeGastos";

import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";
import type { PlantillaGastoResponse } from "@/modules/gastos/dto/plantillaGasto/PlantillaGastoResponse";
import { mapPlantillaGasto } from "@/modules/gastos/mappers/mapPlantillaGasto";

export const plantillaGastoApiQueryRead = {

    async obtenerPorId(id: number): Promise<PlantillaGasto> {
        const response = await api.get<PlantillaGastoResponse>(
            `${ENDPOINTS.plantillaGasto}/${id}`
        );

        return mapPlantillaGasto(response.data);
    },

    async listarTodos(): Promise<PlantillaGasto[]> {
        const response = await api.get<PlantillaGastoResponse[]>(
            ENDPOINTS.plantillaGasto
        );

        return response.data.map(mapPlantillaGasto);
    },

    async listarPorEstado(
        tipoObjeto: tipoObjeto
    ): Promise<PlantillaGasto[]> {
        const response = await api.get<PlantillaGastoResponse[]>(
            ENDPOINTS.plantillaGasto,
            {
                params: { filtro: tipoObjeto },
            }
        );

        return response.data.map(mapPlantillaGasto);
    },
};