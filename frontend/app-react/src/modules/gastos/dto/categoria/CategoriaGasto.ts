import type { tipoObjeto } from "@/shared/types/typesApp";
import { Dayjs } from "dayjs";

export interface CategoriaGasto {
    id: number;
    nombre: string;
    tipo: tipoObjeto;
    fechaCreacion: Dayjs;
}
