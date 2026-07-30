import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";

import { mapCategoriaGasto } from "@/modules/gastos/mappers/mapCategoriaGasto";

import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import type { CategoriaGastoResponse } from "@/modules/gastos/dto/categoria/CategoriaGastoResponse";
import type { tipoObjeto } from "@/modules/gastos/types/typeGastos";

export const categoriaGastoApiQueryRead = {

    async obtenerPorId(id: number): Promise<CategoriaGasto> {
        const response = await api.get<CategoriaGastoResponse>(
            `${ENDPOINTS.categoria}/${id}`
        );

        return mapCategoriaGasto(response.data);
    },

    async listarTodos(): Promise<CategoriaGasto[]> {
        const response = await api.get<CategoriaGastoResponse[]>(
            ENDPOINTS.categoria
        );

        return response.data.map(mapCategoriaGasto);
    },

    async listarPorTipoObjetivo(
        tipoObjetivo: tipoObjeto
    ): Promise<CategoriaGasto[]> {
        const response = await api.get<CategoriaGastoResponse[]>(
            ENDPOINTS.categoria,
            {
                params: { filtro: tipoObjetivo },
            }
        );

        return response.data.map(mapCategoriaGasto);
    },
};