import type { estadoActividadGastoRecurrente } from "@/modules/gastos/types/typeGastos";

import { gastoRecurrenteApiQueryRead } from "../../api/gastoRecurrente/gastoRecurrenteApiQueryRead";
import type { GastoRecurrente } from "../../dto/gastoRecurrente/GastoRecurrente";

export const gastoRecurrenteServiceRead = {

    async obtenerPorId(id: number): Promise<GastoRecurrente> {
        return await gastoRecurrenteApiQueryRead.obtenerPorId(id);
    },

    async listarTodos(): Promise<GastoRecurrente[]>{
        return await gastoRecurrenteApiQueryRead.listarTodos();
    },
    
    async listarPorEstado( estado: estadoActividadGastoRecurrente): Promise<GastoRecurrente[]>{
        return await gastoRecurrenteApiQueryRead.listarPorEstado(estado);
    },
};