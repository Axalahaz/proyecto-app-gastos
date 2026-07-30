import { useContext } from "react";
import { FilterContext } from "@/context/FechaFilterContext"

export const useFilter = () => {
    const context = useContext(FilterContext);

    if (!context) {
        throw new Error("useFilter debe usarse dentro de FilterProvider");
    }

    return context;
}