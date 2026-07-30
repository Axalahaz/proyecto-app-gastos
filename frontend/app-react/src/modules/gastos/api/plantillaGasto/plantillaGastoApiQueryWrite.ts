import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";

import type { RegistrarPlantillaGastoRequest } from "@/modules/gastos/dto/plantillaGasto/RegistrarPlantillaGastoRequest";
import type { EditarPlantillaGastoRequest } from "@/modules/gastos/dto/plantillaGasto/EditarPlantillaGastoRequest";

import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";
import type { PlantillaGastoResponse } from "@/modules/gastos/dto/plantillaGasto/PlantillaGastoResponse";
import { mapPlantillaGasto } from "@/modules/gastos/mappers/mapPlantillaGasto";

export const plantillaGastoApiQueryWrite = {

    async registrar(data: RegistrarPlantillaGastoRequest): Promise<PlantillaGasto> {
        const response = await api.post<PlantillaGastoResponse>(
            ENDPOINTS.plantillaGasto,data
        );

        return mapPlantillaGasto(response.data);
    },

    async editar(id: number, data: EditarPlantillaGastoRequest): Promise<PlantillaGasto> {
        const response = await api.patch<PlantillaGastoResponse>(
            `${ENDPOINTS.plantillaGasto}/${id}`,data
        );

        return mapPlantillaGasto(response.data);
    },

    async eliminar(id: number): Promise<void> {
        await api.delete(`${ENDPOINTS.plantillaGasto}/${id}`);
    }

};