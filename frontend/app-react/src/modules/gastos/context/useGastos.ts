import { useContext } from "react";
import { GastosContext } from "@/modules/gastos/context/GastosContext";

export const useGastos = () => {

    const context = useContext(GastosContext);

    if (!context) {
        throw new Error(
            "useGastos debe utilizarse dentro de GastosProvider"
        );
    }

    return context;
};