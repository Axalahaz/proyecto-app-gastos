import { api } from "@/api/axios";
import { ENDPOINTS } from "@/api/endpoints";

import type { RegistrarCategoriaGastoRequest } from "@/modules/gastos/dto/categoria/RegistrarCategoriaGastoRequest";
import type { EditarCategoriaGastoRequest } from "@/modules/gastos/dto/categoria/EditarCategoriaGastoRequest";

export const categoriaGastoApiQueryWrite = {

    registrar(data: RegistrarCategoriaGastoRequest) {
        return api.post(ENDPOINTS.categoria, data);
    },
    
    editar(data: EditarCategoriaGastoRequest) {
        return api.patch(ENDPOINTS.categoria, data);
    },
    
    eliminar(id: number) {
        return api.delete(`${ENDPOINTS.categoria}/${id}`);
    }

};