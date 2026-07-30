import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import { Dayjs } from "dayjs";

import { FILTERS } from "@/context/typesFilter"
import type { FilterType } from "@/context/typesFilter"

import { esMismoDia, esMismoMes, esMismoAnio } from "@/shared/utils/compareFechasUtils";

export const useFiltrarGastos = (
    gastos: Gasto[], 
    active: FilterType,
    selectedDate: Dayjs,
) => {

    return gastos.filter((gasto) => {
        const fecha = gasto.fechaCreacion;

        switch (active) {
        case FILTERS.DIARIO.value:
            return esMismoDia(fecha, selectedDate);

        case FILTERS.MENSUAL.value:
            return esMismoMes(fecha, selectedDate);

        case FILTERS.ANUAL.value:
            return esMismoAnio(fecha, selectedDate);

        case FILTERS.PERIODO.value:
            return true; //!! luego mejorar con rango personalizado

        default:
            return true;
        }
    });
};