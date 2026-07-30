import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";

import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { GastoResponse } from "@/modules/gastos/dto/gasto/GastoResponse";

import type { RegistrarGastoRequest } from "@/modules/gastos/dto/gasto/RegistrarGastoRequest";
import type { RegistrarGastoDesdeRecurrente } from "@/modules/gastos/dto/gasto/RegistrarGastoDesdeRecurrente";
import type { RegistrarGastoDesdeRecurrenteRequest } from "@/modules/gastos/dto/gasto/RegistrarGastoDesdeRecurrenteRequest";
import type { EditarGastoRequest } from "@/modules/gastos/dto/gasto/EditarGastoRequest";
import type { VolverRecurrenteRequest } from "@/modules/gastos/dto/gasto/VolverRecurrenteRequest";
import { mapGasto, mapRegistrarGasto } from "@/modules/gastos/mappers/mapGasto";

export const gastoApiQueryWrite = {

    async registrar(data: RegistrarGastoRequest): Promise<Gasto> {
        const response = await api.post<GastoResponse>(ENDPOINTS.gasto, data);
        return mapGasto(response.data);
    },
    
    async registrarDesdeRecurrente(data: RegistrarGastoDesdeRecurrente): Promise<Gasto> {
        const dataRequest: RegistrarGastoDesdeRecurrenteRequest = mapRegistrarGasto(data);
        const response = await api.post<GastoResponse>(
            `${ENDPOINTS.gasto}/recurrente`, dataRequest
        );
        return mapGasto(response.data);
    },

    async editar(id: number, data: EditarGastoRequest): Promise<Gasto> {
        const response = await api.patch<GastoResponse>(
            `${ENDPOINTS.gasto}/${id}`, data
        );
        return mapGasto(response.data);
    },
    
    async volverRecurrente(id: number, data: VolverRecurrenteRequest): Promise<Gasto> {
        const response = await api.patch<GastoResponse>(
            `${ENDPOINTS.gasto}/${id}/volver-recurrente`, data
        );
        return mapGasto(response.data);
    },
    
    async anular(id: number): Promise<void>  {
        await api.patch(`${ENDPOINTS.gasto}/${id}/anular`);
    },

    async eliminar(id: number): Promise<void>  {
        await api.delete(`${ENDPOINTS.gasto}/${id}`);
    }

};