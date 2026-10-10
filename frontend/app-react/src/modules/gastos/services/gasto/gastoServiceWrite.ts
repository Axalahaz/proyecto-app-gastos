import { gastoApiQueryWrite } from "@/modules/gastos/api/gasto/gastoApiQueryWrite";
import type { RegistrarGastoRequest } from "@/modules/gastos/dto/gasto/RegistrarGastoRequest";
import type { EditarGastoRequest } from "@/modules/gastos/dto/gasto/EditarGastoRequest";
import type { VolverRecurrenteRequest } from "@/modules/gastos/dto/gasto/VolverRecurrenteRequest";

import toast from "react-hot-toast";
import type { RegistrarGastoDesdeRecurrente } from "../../dto/gasto/RegistrarGastoDesdeRecurrente";

export const gastoServiceWrite = {

    async registrar(data: RegistrarGastoRequest) {
        return toast.promise(
            gastoApiQueryWrite.registrar(data),
            {
                loading: "Guardando...",
                success: "Gasto registrado correctamente",
                error: "Error en el registro",
            }
        );
    },

    async registrarDesdeRecurrente(data: RegistrarGastoDesdeRecurrente) {
        return toast.promise(
            gastoApiQueryWrite.registrarDesdeRecurrente(data),
            {
                loading: 'Guardando...',
                success: "Gasto desde Recurrente registrado correctamente",
                error: "Error en el registro",
            }
        );
    },
    
    async editar (id: number, data: EditarGastoRequest) {
        return toast.promise(
            gastoApiQueryWrite.editar(id, data),
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
    
}