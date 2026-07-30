import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";

import type { RegistrarGastoRecurrenteRequest } from "@/modules/gastos/dto/gastoRecurrente/RegistrarGastoRecurrenteRequest";
import type { EditarPeriodicidadRequest } from "@/modules/gastos/dto/gastoRecurrente/EditarPeriodicidadRequest";
import type { EditarGastoRecurrenteSinPeriodicidadRequest } from "@/modules/gastos/dto/gastoRecurrente/EditarGastoRecurrenteSinPeriodicidadRequest";

import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { GastoRecurrenteResponse } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteResponse";
import { mapGastoRecurrente } from "@/modules/gastos/mappers/mapGastoRecurrente";

export const gastoRecurrenteApiQueryWrite = {

    async registrar(data: RegistrarGastoRecurrenteRequest): Promise<GastoRecurrente> {
        const response = await api.post<GastoRecurrenteResponse>(
            ENDPOINTS.gastoRecurrente, data
        );

        return mapGastoRecurrente(response.data);
    },

    async editarBasico(
        id: number,
        data: EditarGastoRecurrenteSinPeriodicidadRequest
    ): Promise<GastoRecurrente> {
        const response = await api.patch<GastoRecurrenteResponse>(
            `${ENDPOINTS.gastoRecurrente}/${id}/basico`,data
        );

        return mapGastoRecurrente(response.data);
    },

    async editarPeriocidad(
        id: number,
        data: EditarPeriodicidadRequest
    ): Promise<GastoRecurrente> {
        const response = await api.patch<GastoRecurrenteResponse>(
            `${ENDPOINTS.gastoRecurrente}/${id}/periodicidad`, data
        );

        return mapGastoRecurrente(response.data);
    },

    async activar(id: number): Promise<void> {
        await api.patch(`${ENDPOINTS.gastoRecurrente}/${id}/activar`);
    },

    async desactivar(id: number): Promise<void> {
        await api.patch(`${ENDPOINTS.gastoRecurrente}/${id}/desactivar`);
    },

    async eliminar(id: number): Promise<void> {
        await api.delete(`${ENDPOINTS.gastoRecurrente}/${id}`);
    }

};