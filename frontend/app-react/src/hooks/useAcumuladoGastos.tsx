import type { Gasto} from "@/modules/gastos/dto/gasto/Gasto";

export const useAcumuladoGastos = (gastos: Gasto[]) => {
    return gastos.reduce(
        (total, gasto) => total + gasto.monto,
        0
    );
};