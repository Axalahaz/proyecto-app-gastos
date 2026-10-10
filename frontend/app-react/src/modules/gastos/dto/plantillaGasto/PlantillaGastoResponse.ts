import type { tipoObjeto } from "@/shared/types/typesApp";

export interface PlantillaGastoResponse {
	id: number;
	categoriaGastoId: number;
    descripcion: string;
    tipo: tipoObjeto;
    fechaCreacion: string;
}