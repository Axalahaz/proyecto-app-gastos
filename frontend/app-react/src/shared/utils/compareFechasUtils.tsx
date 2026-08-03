import type { Dayjs } from "dayjs";

export const esMismoDia = (fecha: Dayjs, hoy: Dayjs): boolean =>
    fecha.isSame(hoy, "day");

export const esMismoMes = (fecha: Dayjs, hoy: Dayjs): boolean =>
    fecha.isSame(hoy, "month");

export const esMismoAnio = (fecha: Dayjs, hoy: Dayjs): boolean =>
    fecha.isSame(hoy, "year");

export const formatDateInput = (fecha: Dayjs): string =>
    fecha.format("DD/MM/YYYY");