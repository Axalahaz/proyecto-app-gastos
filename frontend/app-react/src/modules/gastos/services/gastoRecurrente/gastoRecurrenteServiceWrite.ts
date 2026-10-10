import toast from "react-hot-toast";

import type { RegistrarGastoRecurrenteRequest } from "@/modules/gastos/dto/gastoRecurrente/RegistrarGastoRecurrenteRequest";
import type { EditarPeriodicidadRequest } from "@/modules/gastos/dto/gastoRecurrente/EditarPeriodicidadRequest";
import type { EditarGastoRecurrenteSinPeriodicidadRequest } from "@/modules/gastos/dto/gastoRecurrente/EditarGastoRecurrenteSinPeriodicidadRequest";
import { gastoRecurrenteApiQueryWrite } from "../../api/gastoRecurrente/gastoRecurrenteApiQueryWrite";
import type { GastoRecurrente } from "../../dto/gastoRecurrente/GastoRecurrente";

export const gastoRecurrenteServiceWrite = {

    async registrar(data: RegistrarGastoRecurrenteRequest): Promise<GastoRecurrente> {
        return toast.promise(
            gastoRecurrenteApiQueryWrite.registrar(data),
            {
                loading: "Guardando...",
                success: "Gasto Recurrente registrado correctamente",
                error: "Error en el registro",
            }
        );
    },

    async editarBasico (
        id: number, 
        data: EditarGastoRecurrenteSinPeriodicidadRequest
    ): Promise<GastoRecurrente> {
        return toast.promise(
            gastoRecurrenteApiQueryWrite.editarBasico(id, data),
            {
                loading: 'Actualizando básico...',
                success: "Gasto Recurrente actualizado correctamente",
                error: "Error en la actualizacion",
            }
        );
    },

    async editarPeriocidad (
        id: number, 
        data: EditarPeriodicidadRequest
    ): Promise<GastoRecurrente> {
        return toast.promise(
            gastoRecurrenteApiQueryWrite.editarPeriocidad(id, data),
            {
                loading: 'Actualizando periodicidad...',
                success: "Gasto Recurrente actualizado correctamente",
                error: "Error en la actualizacion",
            }
        );
    },
    

    async activar(id: number): Promise<void> {
        return toast.promise(
            gastoRecurrenteApiQueryWrite.activar(id),
            {
                loading: "Activnado...",
                success: "Gasto Recurrente activado correctamente",
                error: "Error en la activación",
            }
        );
    },

    async desactivar(id: number): Promise<void> {
        return toast.promise(
            gastoRecurrenteApiQueryWrite.desactivar(id),
            {
                loading: "Desactivar...",
                success: "Gasto Recurrente desactivado correctamente",
                error: "Error en la desactivación",
            }
        );
    },

    async eliminar(id: number): Promise<void> {
        return toast.promise(
            gastoRecurrenteApiQueryWrite.eliminar(id),
            {
                loading: "Eliminando...",
                success: "Gasto Recurrente eliminado correctamente",
                error: "Error en la eliminación",
            }
        );
    },

};