import dayjs from "dayjs";
import { TIPO_OBJETO, } from "@/shared/types/typesApp";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";

export const categoriasGastoMock: CategoriaGasto[] = [
    {
        id: 1,
        nombre: "Compras",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-01-10"),
    },
    {
        id: 2,
        nombre: "Transporte",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-01-12"),
    },
    {
        id: 3,
        nombre: "Servicios",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-01-15"),
    },
    {
        id: 4,
        nombre: "Plataformas",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-02-01"),
    },
    {
        id: 5,
        nombre: "Salud",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-02-05"),
    },
    {
        id: 6,
        nombre: "Hogar",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-02-05"),
    },
];