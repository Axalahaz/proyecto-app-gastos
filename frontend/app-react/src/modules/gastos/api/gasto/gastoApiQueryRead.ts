import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";
import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { GastoResponse } from "@/modules/gastos/dto/gasto/GastoResponse";
import { mapGasto } from "@/modules/gastos/mappers/mapGasto";

export const gastoApiQueryRead = {

    async obtenerPorId(id: number): Promise<Gasto> {
        const response = await api.get<GastoResponse>(
            `${ENDPOINTS.gasto}/${id}`
        );

        return mapGasto(response.data);
    },

    async listarTodos(): Promise<Gasto[]> {
        const response = await api.get<GastoResponse[]>(
            ENDPOINTS.gasto
        );

        return response.data.map(mapGasto);
    },

    async listarPorCategoria(categoriaId: number): Promise<Gasto[]> {
        const response = await api.get<GastoResponse[]>(
            ENDPOINTS.gasto,
            {
                params: { categoria: categoriaId },
            }
        );

        return response.data.map(mapGasto);
    },
};