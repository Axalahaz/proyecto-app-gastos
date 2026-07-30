import type { tipoObjeto } from "@/modules/gastos/types/typeGastos";
import { Dayjs } from "dayjs";

export interface CategoriaGasto {
    id: number;
    nombre: string;
    tipo: tipoObjeto;
    fechaCreacion: Dayjs;
}
