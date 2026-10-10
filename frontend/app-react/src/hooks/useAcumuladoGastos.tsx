import type { Gasto} from "@/modules/gastos/dto/gasto/Gasto";
import { ESTADO_MOVIMIENTO } from "@/shared/types/typesApp";

export const useAcumuladoGastos = (gastos: Gasto[]) => {

    const gastosActivos = gastos.filter(
        g => g.estado === ESTADO_MOVIMIENTO.ACTIVO
    )
    
    return gastosActivos.reduce(
        (total, gasto) => total + gasto.monto,
        0
    );
};