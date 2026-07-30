import { gastoApiQueryWrite } from "@/modules/gastos/api/gasto/gastoApiQueryWrite";
import type { RegistrarGastoRequest } from "@/modules/gastos/dto/gasto/RegistrarGastoRequest";
import type { RegistrarGastoDesdeRecurrenteRequest } from "@/modules/gastos/dto/gasto/RegistrarGastoDesdeRecurrenteRequest";
import type { ActualizarGastoRequest } from "@/modules/gastos/dto/gasto/EditarGastoRequest";
import type { VolverRecurrenteRequest } from "@/modules/gastos/dto/gasto/VolverRecurrenteRequest";

import toast from "react-hot-toast";

export const gastoService = {
    
    async eliminar(id: number){
        return toast.promise(
            gastoApiQueryWrite.eliminar(id),
            {
                loading: "Eliminando...",
                success: "Gasto eliminado correctamente",
                error: "Error en la eliminación",
            }
        );
    },

    async crear(data: RegistrarGastoRequest) {
        return toast.promise(
            gastoApiQueryWrite.crear(data),
            {
                loading: "Guardando...",
                success: "Gasto registrado correctamente",
                error: "Error en el registro",
            }
        );
    },

    async crearDesdeRecurrente(data: RegistrarGastoDesdeRecurrenteRequest) {
        return toast.promise(
            gastoApiQueryWrite.crearDesdeRecurrente(data),
            {
                loading: 'Guardando...',
                success: "Gasto desde Recurrente registrado correctamente",
                error: "Error en el registro",
            }
        );
    },
    
    async actualizar(id: number, data: ActualizarGastoRequest) {
        return toast.promise(
            gastoApiQueryWrite.actualizar(id, data),
            {
                loading: 'Actualizando...',
                success: "Gasto actualizado correctamente",
                error: "Error en la actualizacion",
            }
        );
    },
    
    async volverRecurrente(id: number, data: VolverRecurrenteRequest) {
        return toast.promise(
            gastoApiQueryWrite.volverRecurrente(id, data),
            {
                loading: 'Guardando...',
                success: "Gasto cambiado a Recurrente correctamente",
                error: "Error en el cambio",
            }
        );
    },
    
    async anular(id: number) {
        return toast.promise(
            gastoApiQueryWrite.anular(id),
            {
                loading: 'Guardando...',
                success: "Gasto anulado correctamente",
                error: "Error en la anulacion",
            }
        );
    },
    
}