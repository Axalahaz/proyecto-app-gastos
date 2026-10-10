import type { tipoObjeto } from "@/shared/types/typesApp";

export interface CategoriaGastoResponse {
	id: number;
    nombre: string;
    tipo: tipoObjeto;
    fechaCreacion: string;
}