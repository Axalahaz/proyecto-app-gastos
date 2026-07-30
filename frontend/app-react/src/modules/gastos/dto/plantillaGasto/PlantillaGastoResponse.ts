import type { tipoObjeto } from "@/modules/gastos/types/typeGastos";

export interface PlantillaGastoResponse {
	id: number;
	categoriaGastoId: number;
    descripcion: string;
    tipo: tipoObjeto;
    fechaCreacion: string;
}