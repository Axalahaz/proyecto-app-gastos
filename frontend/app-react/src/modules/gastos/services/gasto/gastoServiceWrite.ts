import { gastoApiQueryRead } from "@/modules/gastos/api/gasto/gastoApiQueryRead";

export const gastoService = {

    async listarTodos(){
        const response = await gastoApiQueryRead.listarTodos();
        return response.data;
    },
    
    async listarPorCategoria(categoriaId: number){
        const response = await gastoApiQueryRead.listarPorCategoria(categoriaId);
        return response.data;
    },

    async obtenerPorId(id: number) {
        await gastoApiQueryRead.obtenerPorId(id);
    },
}