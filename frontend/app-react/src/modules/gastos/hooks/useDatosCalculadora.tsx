import { useEffect, useMemo, useState } from "react";

import type { FormularioGasto } from "@/modules/gastos/dto/gasto/FormularioGasto";
import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";

type Props = {
    gastoSeleccionado: Gasto | null;
    gastoRecurrenteSeleccionado: GastoRecurrente | null;
    categoriaGastoSeleccionada: CategoriaGasto | null;
    total: number;
    categoriasGastos: CategoriaGasto[];
    plantillasGastos: PlantillaGasto[];
    plantillaGastoSeleccionada: PlantillaGasto | null;
}

export const useDatosCalculadora = ({
    gastoSeleccionado,
    categoriaGastoSeleccionada, 
    gastoRecurrenteSeleccionado,
    total,
    categoriasGastos,
    plantillasGastos,
    plantillaGastoSeleccionada,
}: Props) => {

    // ----------------------------
    // PLANTILLA: activacion por GASTO o GASTO RECURRENTE
    const plantillaSeleccionada = useMemo(() => {

        if (gastoRecurrenteSeleccionado) {
            return plantillasGastos.find(
                p => p.id === gastoRecurrenteSeleccionado.plantillaId
            ) ?? null;
        }
        if (gastoSeleccionado) {
            return plantillasGastos.find(
                p => p.id === gastoSeleccionado.plantillaId
            ) ?? null;
        }
        return null;

    }, [
        gastoRecurrenteSeleccionado,
        gastoSeleccionado,
        plantillasGastos,
    ]);

    const nombrePlantilla = plantillaSeleccionada?.descripcion
        ?? gastoRecurrenteSeleccionado?.descripcion 
        ?? gastoSeleccionado?.descripcion
        ?? "";
    
    // -----------------------------
    // CATEGORIA
    const categoriaSeleccionada = useMemo(() => {

        if (categoriaGastoSeleccionada) {
            return categoriaGastoSeleccionada;
        }
        else if (plantillaSeleccionada) {
            return categoriasGastos.find(
                c => c.id === plantillaSeleccionada.categoriaGastoId
            ) ?? null;
        }
        else if (gastoRecurrenteSeleccionado) {
            return categoriasGastos.find(
                c => c.id === gastoRecurrenteSeleccionado.categoriaGastoId
            ) ?? null;
        }
        else if (gastoSeleccionado) {
            return categoriasGastos.find(
                c => c.id === gastoSeleccionado.categoriaGastoId
            ) ?? null;
        }

        return null;
    }, [
        categoriaGastoSeleccionada,
        plantillaSeleccionada,
        gastoRecurrenteSeleccionado,
        gastoSeleccionado,
        categoriasGastos,
    ]);

    const nombreCategoria = categoriaSeleccionada?.nombre ?? "";

    //------------------
    // Seleccion de plantillas desde gasto nuevo
    const plantillasPorCategoria = useMemo(() => {
        if (!categoriaGastoSeleccionada) {
            return [];
        }

        return plantillasGastos.filter(
            p => p.categoriaGastoId === categoriaGastoSeleccionada.id
        );
    }, [plantillasGastos, categoriaGastoSeleccionada]);

    //------------------
    // FORMULARIO DATOS
    const [formGasto, setFormGasto] = useState<FormularioGasto>({
        categoriaGastoId: gastoSeleccionado?.categoriaGastoId,
        plantillaId: gastoSeleccionado?.plantillaId,
        gastoRecurrenteId: 
            gastoSeleccionado?.gastoRecurrenteId ??
            gastoRecurrenteSeleccionado?.id,
        monto: gastoSeleccionado?.monto ?? total,
        descripcion: 
            gastoSeleccionado?.descripcion ??
            gastoRecurrenteSeleccionado?.descripcion ??
            "",
        estado: gastoSeleccionado?.estado,
    });

    useEffect(() => {
        if (!plantillaGastoSeleccionada) return;

        const descripcion = plantillaGastoSeleccionada.descripcion;

        setFormGasto(prev => ({
            ...prev,
            descripcion,
            plantillaId: plantillaGastoSeleccionada.id,
        }));

    }, [plantillaGastoSeleccionada]);
    
    return {
        categoriaSeleccionada,
        plantillaSeleccionada,
        nombreCategoria,
        nombrePlantilla,
        plantillasPorCategoria,
        gastoRecurrenteSeleccionado,

        formGasto,
        setFormGasto,
    };
}