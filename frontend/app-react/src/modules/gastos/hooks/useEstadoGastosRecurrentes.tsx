import type { GastoRecurrenteVencimiento } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteVencimiento";
import { useGastos } from "@/modules/gastos/context/useGastos";
import { useMemo } from "react";
import type { GastoRecurrenteViewModel } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteViewModel";
import { ESTADO_FECHA_GASTO_RECORRENTE } from "@/modules/gastos/types/typeGastos";
import type { estadoFechaGastoRecurrente } from "@/modules/gastos/types/typeGastos";

import dayjs from "dayjs";

export const useEstadoGastosRecurrentes = (
    gastosRecurrentesOrdenados: GastoRecurrenteVencimiento[]
) => {

    const {gastos} = useGastos();

    const gastosRecurrentesConEstado = useMemo(() =>{
        const fechaHoy = dayjs().startOf("day");
        const listaConEstado: GastoRecurrenteViewModel[] = [];

        for (const gr of gastosRecurrentesOrdenados) {
            let estado: estadoFechaGastoRecurrente;

            const gasto = gastos.find(
                g => g.gastoRecurrenteId === gr.gastoRecurrente.id 
                && dayjs(g.fechaVencimiento).isSame(gr.actualVencimiento, "day")
            );

            if (gasto) {
                // PAGADO
                estado = ESTADO_FECHA_GASTO_RECORRENTE.PAGADO;
            } else if (dayjs(gr.actualVencimiento).isBefore(fechaHoy)) {
                // VENCIDO
                estado= ESTADO_FECHA_GASTO_RECORRENTE.VENCIDO;
            } else if (gr.diasRestantes <= 7) {
                // POR_VENCER
                estado= ESTADO_FECHA_GASTO_RECORRENTE.POR_VENCER;
            } else {
                // PENDIENTE
                estado= ESTADO_FECHA_GASTO_RECORRENTE.PROGRAMADO;
            }
            listaConEstado.push({
                gastoRecurrente: gr.gastoRecurrente,
                actualVencimiento: gr.actualVencimiento,
                proximoVencimiento: gr.proximoVencimiento,
                diasRestantes: gr.diasRestantes,
                estado
            });
        }

        return listaConEstado;

    }, [gastosRecurrentesOrdenados, gastos]);

    return gastosRecurrentesConEstado;
}