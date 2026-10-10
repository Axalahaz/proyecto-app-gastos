import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";

import { mapCategoriaGasto } from "@/modules/gastos/api/mappers/mapCategoriaGasto";

import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import type { CategoriaGastoResponse } from "@/modules/gastos/dto/categoria/CategoriaGastoResponse";
import type { tipoObjetoFilter } from "@/shared/types/typesApp";

export const categoriaGastoApiQueryRead = {

    async obtenerPorId(id: number): Promise<CategoriaGasto> {
        const response = await api.get<CategoriaGastoResponse>(
            `${ENDPOINTS.categoria}/${id}`
        );

        return mapCategoriaGasto(response.data);
    },

    async listar(
        tipoObjetoFilter: tipoObjetoFilter 
    ): Promise<CategoriaGasto[]> {
        const response = await api.get<CategoriaGastoResponse[]>(
            ENDPOINTS.categoria,
            {
                params: { filtro: tipoObjetoFilter },
            }
        );
        return response.data.map(mapCategoriaGasto);
    },
};