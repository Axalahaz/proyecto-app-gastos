import { gastoApiQueryRead } from "@/modules/gastos/api/gasto/gastoApiQueryRead";
import type { Gasto } from "../../dto/gasto/Gasto";

export const gastoServiceRead = {
    
    async obtenerPorId(id: number): Promise<Gasto>  {
        return await gastoApiQueryRead.obtenerPorId(id);
    },

    async listarTodos(): Promise<Gasto[]> {
        return await gastoApiQueryRead.listarTodos();
    },
    
    async listarPorCategoria(categoriaId: number): Promise<Gasto[]> {
        return await gastoApiQueryRead.listarPorCategoria(categoriaId);
    },
}