import type { tipoObjeto } from "@/shared/types/typesApp";
import { Dayjs } from "dayjs";

export interface PlantillaGasto {
	id: number;
	categoriaGastoId: number;
    descripcion: string;
    tipo: tipoObjeto;
    fechaCreacion: Dayjs;
}