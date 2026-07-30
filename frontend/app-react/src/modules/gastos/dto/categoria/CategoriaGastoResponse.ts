import type { tipoObjeto } from "@/modules/gastos/types/typeGastos";

export interface CategoriaGastoResponse {
	id: number;
    nombre: string;
    tipo: tipoObjeto;
    fechaCreacion: string;
}