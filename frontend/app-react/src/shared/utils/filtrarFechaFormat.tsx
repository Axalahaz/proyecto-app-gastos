import type { FilterType } from "@/context/typesFilter";
import { FILTERS } from "@/context/typesFilter";

import fecha from "@/shared/utils/stringFechaFormat";

import { Dayjs } from "dayjs";

export const filtrarFechaFormat = (
    filter: FilterType,
    date1: Dayjs,
    date2?: Dayjs
) => {

    switch (filter) {

        case FILTERS.DIARIO.value:
            return fecha.formatDiaMesAno(date1);

        case FILTERS.MENSUAL.value:
            return fecha.formatMes(date1);

        case FILTERS.ANUAL.value:
            return fecha.formatAnio(date1);

        case FILTERS.PERIODO.value:
            if (date2) return fecha.formatDiaMesAnoDosFechas(date1, date2);
            return "Error"
        default:
            return "";
    }
};