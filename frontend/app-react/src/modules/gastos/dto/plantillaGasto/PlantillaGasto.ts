import type { tipoObjeto } from "@/modules/gastos/types/typeGastos";
import { Dayjs } from "dayjs";

export interface PlantillaGasto {
	id: number;
	categoriaGastoId: number;
    descripcion: string;
    tipo: tipoObjeto;
    fechaCreacion: Dayjs;
}