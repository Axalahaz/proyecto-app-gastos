import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";
import type { estadoActividadGastoRecurrente } from "@/modules/gastos/types/typeGastos";

import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { GastoRecurrenteResponse } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteResponse";
import { mapGastoRecurrente } from "@/modules/gastos/mappers/mapGastoRecurrente";

export const gastoRecurrenteApiQueryRead = {

    async obtenerPorId(id: number): Promise<GastoRecurrente> {
        const response = await api.get<GastoRecurrenteResponse>(
            `${ENDPOINTS.gastoRecurrente}/${id}`
        );

        return mapGastoRecurrente(response.data);
    },

    async listarTodos(): Promise<GastoRecurrente[]> {
        const response = await api.get<GastoRecurrenteResponse[]>(
            ENDPOINTS.gastoRecurrente
        );

        return response.data.map(mapGastoRecurrente);
    },

    async listarPorEstado(
        estado: estadoActividadGastoRecurrente
    ): Promise<GastoRecurrente[]> {
        const response = await api.get<GastoRecurrenteResponse[]>(
            ENDPOINTS.gastoRecurrente,
            {
                params: { filtro: estado },
            }
        );

        return response.data.map(mapGastoRecurrente);
    },
};