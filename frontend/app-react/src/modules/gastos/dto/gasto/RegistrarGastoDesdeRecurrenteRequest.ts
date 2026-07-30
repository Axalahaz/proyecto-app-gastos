export interface RegistrarGastoDesdeRecurrenteRequest {
	monto: number;
	gastoRecurrenteId: number; 
	categoriaGastoId: number;
	fechaVencimiento: string;
}