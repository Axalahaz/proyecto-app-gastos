import type { GastoRecurrente} from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import dayjs from "dayjs";
import type { Dayjs } from "dayjs";

type Vencimientos = {
    actual: Dayjs;
    proximo: Dayjs;
}

export const obtenerVencimientos = (
    item: GastoRecurrente
): Vencimientos  => {
    const fechaHoy = dayjs().startOf("day");
    const mesHoy = fechaHoy.month(); // 0-11
    const anioHoy = fechaHoy.year(); 

    const dia: number = item.diaVencimiento; // dayj 1-31

    // Frecuencia mensual
    if(item.mesVencimiento === null) {
        const fechaItem = dayjs()
            .year(anioHoy)
            .month(mesHoy)
            .date(dia)
            .startOf("day");

        if (fechaItem.isBefore(fechaHoy)) {
            return {
                actual: fechaItem, 
                proximo: fechaItem.add(1, "month")
            };
        }
        return {
            actual: fechaItem,
            proximo: fechaItem
        };
    } 

    // Frecuencia anual
    const fechaItem = dayjs()
        .year(anioHoy)
        .month(item.mesVencimiento - 1)
        .date(dia)
        .startOf("day");

    if (fechaItem.isBefore(fechaHoy)) {
        return {
            actual: fechaItem,
            proximo: fechaItem.add(1, "year"),
        };
    }

    return {
        actual: fechaItem,
        proximo: fechaItem,
    };
};

export const diasHastaVencimiento = (
    proximoVencimiento: Dayjs
): number => {
    return proximoVencimiento
        .startOf("day")
        .diff(dayjs().startOf("day"), "day");
};