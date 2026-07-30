export interface RegistrarGastoRequest {
	monto: number;
    descripcion: string | null;
    categoriaGastoId: number;
}