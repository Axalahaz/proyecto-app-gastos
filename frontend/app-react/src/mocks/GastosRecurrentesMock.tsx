import dayjs from "dayjs";

import { FRECUENCIA } from "@/modules/gastos/types/typeGastos";

import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";

export const gastosRecurrentesMock: GastoRecurrente[] = [
    {
        id: 1,
        categoriaGastoId: 6,
        plantillaId: null,
        descripcion: "Alquiler",
        diaVencimiento: 31,
        mesVencimiento: null,
        frecuencia: FRECUENCIA.MENSUAL,
        activo: true,
        fechaCreacion: dayjs("2026-01-05"),
        fechaCambioEstado: null,
    },
    {
        id: 2,
        categoriaGastoId: 3,
        plantillaId: 4,
        descripcion: "Edenor",
        diaVencimiento: 17,
        mesVencimiento: null,
        frecuencia: FRECUENCIA.MENSUAL,
        activo: true,
        fechaCreacion: dayjs("2026-02-10"),
        fechaCambioEstado: null,
    },
    {
        id: 3,
        categoriaGastoId: 2,
        plantillaId: null,
        descripcion: "Patente",
        diaVencimiento: 20,
        mesVencimiento: 2,
        frecuencia: FRECUENCIA.ANUAL,
        activo: true,
        fechaCreacion: dayjs("2026-03-01"),
        fechaCambioEstado: null,
    },
    {
        id: 4,
        categoriaGastoId: 5,
        plantillaId: null,
        descripcion: "Prepagaga",
        diaVencimiento: 10,
        mesVencimiento: null,
        frecuencia: FRECUENCIA.MENSUAL,
        activo: true,
        fechaCreacion: dayjs("2026-01-15"),
        fechaCambioEstado: null,
    },
    {
        id: 6,
        categoriaGastoId: 3,
        plantillaId: null,
        descripcion: "Aysa",
        diaVencimiento: 30,
        mesVencimiento: null,
        frecuencia: FRECUENCIA.MENSUAL,
        activo: true,
        fechaCreacion: dayjs("2026-04-20"),
        fechaCambioEstado: null,
    },
];