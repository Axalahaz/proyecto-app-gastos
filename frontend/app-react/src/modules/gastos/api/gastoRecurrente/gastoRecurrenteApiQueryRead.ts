import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";
import type { estadoGastoRecurrenteFilter } from "@/modules/gastos/types/typeGastos";

import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { GastoRecurrenteResponse } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteResponse";
import { mapGastoRecurrente } from "@/modules/gastos/api/mappers/mapGastoRecurrente";

export const gastoRecurrenteApiQueryRead = {

    async obtenerPorId(id: number): Promise<GastoRecurrente> {
        const response = await api.get<GastoRecurrenteResponse>(
            `${ENDPOINTS.gastoRecurrente}/${id}`
        );

        return mapGastoRecurrente(response.data) ?? null;
    },

    async listar(
        estado: estadoGastoRecurrenteFilter
    ): Promise<GastoRecurrente[]> {
        const response = await api.get<GastoRecurrenteResponse[]>(
            ENDPOINTS.gastoRecurrente,
            {
                params: { filtro: estado },
            }
        );

        return response.data
            .map(g => mapGastoRecurrente(g));
    },
};