import { useMemo } from "react";

import type { GastoRecurrenteVencimiento } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteVencimiento";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";

import { obtenerVencimientos } from "@/modules/gastos/utils/gastoRecurrenteUtils";
import { diasHastaVencimiento } from "@/modules/gastos/utils/gastoRecurrenteUtils";


export const useGastosRecurrentesOrdenados = (listGastosRecurrentes: GastoRecurrente[]) => {

    const gastosRecurrentesOrdenados = useMemo(() => {
        const gastosRecurrentesModificados: GastoRecurrenteVencimiento[] = 
            listGastosRecurrentes
                .map(g => {
                    const vencimientos = obtenerVencimientos(g);
                    const diasRestantes = diasHastaVencimiento(vencimientos.proximo);

                    return {
                            gastoRecurrente: g,
                            actualVencimiento: vencimientos.actual,
                            proximoVencimiento: vencimientos.proximo,
                            diasRestantes: diasRestantes
                        };
        });

        gastosRecurrentesModificados.sort(
            (a, b) => a.proximoVencimiento.diff(b.proximoVencimiento)
        );

        return gastosRecurrentesModificados;
    }, [listGastosRecurrentes]);

    return gastosRecurrentesOrdenados;
}