import dayjs from "dayjs";
import { TIPO_OBJETO } from "@/shared/types/typesApp";
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";

export const plantillasGastoMock: PlantillaGasto[] = [
    {
        id: 1,
        categoriaGastoId: 1,
        descripcion: "Verduleria",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-01-10"),
    },
    {
        id: 2,
        categoriaGastoId: 2,
        descripcion: "BoletoTransporte",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-01-15"),
    },
    {
        id: 3,
        categoriaGastoId: 4,
        descripcion: "Netflix",
        tipo: TIPO_OBJETO.USUARIO,
        fechaCreacion: dayjs("2026-02-01"),
    },
    {
        id: 4,
        categoriaGastoId: 3,
        descripcion: "Luz",
        tipo: TIPO_OBJETO.SISTEMA,
        fechaCreacion: dayjs("2026-02-10"),
    },
    {
        id: 5,
        categoriaGastoId: 5,
        descripcion: "Anticonseptivas",
        tipo: TIPO_OBJETO.USUARIO,
        fechaCreacion: dayjs("2026-03-05"),
    },
];