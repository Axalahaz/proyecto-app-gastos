import { useState } from "react";
import type { ReactNode } from "react";

import { GastosContext } from "@/modules/gastos/context/GastosContext";
import { gastoApiQueryRead } from "@/modules/gastos/api/gasto/gastoApiQueryRead";
import { categoriaGastoApiQueryRead } from "@/modules/gastos/api/categoriaGasto/CategoriaGastoApiQueryRead";
import { gastoRecurrenteApiQueryRead } from "@/modules/gastos/api/gastoRecurrente/gastoRecurrenteApiQueryRead";
import { plantillaGastoApiQueryRead } from "@/modules/gastos/api/plantillaGasto/plantillaGastoApiQueryRead";

import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";
import type { SidebarMode } from "@/shared/types/typeSidebar";
import { SIDEBAR } from "@/shared/types/typeSidebar";

interface Props {
    children: ReactNode;
}

export const GastosProvider = ({
    children
}: Props) => {

    // !!! Luego extraer repeticiones en un hook!
    /**
    const {
        lista,
        seleccion,
        cargar,
        seleccionar
    } = useCrudResource(...)
     */
    // -----------------
    // GASTOS
    const [gastos, setGastos] = useState<Gasto[]>([]);
    const [gastoSeleccionado, setGastoSeleccionado] = useState<Gasto>();

    const cargarGastos = async () => {
            const listGastos = await gastoApiQueryRead.listarTodos();
            setGastos(listGastos);
    };

    const seleccionarGasto = (gasto?: Gasto) => {
        setGastoSeleccionado(gasto);
    }

    // -----------------
    // CATEGORIA GASTO
    const [categoriasGastos, setCategoriasGastos] = useState<CategoriaGasto[]>([]);
    const [categoriaGastoSeleccionada, setCategoriaGastoSeleccionada] = useState<CategoriaGasto>();

    const cargarCategoriasGastos = async () => {
            const listCategorias = await categoriaGastoApiQueryRead.listarTodos();
            setCategoriasGastos(listCategorias);
    };

    const seleccionarCategoriaGasto = (categoriaGasto?: CategoriaGasto) => {
        setCategoriaGastoSeleccionada(categoriaGasto);
    }

    // -----------------
    // GASTOS RECURRENTES
    const [gastosRecurrentes, setGastosRecurrentes] = useState<GastoRecurrente[]>([]);
    const [gastoRecurrenteSeleccionado, setGastoRecurrenteSeleccionado] = useState<GastoRecurrente>();

    const cargarGastosRecurrentes = async () => {
            const listGastosRecurrentes = await gastoRecurrenteApiQueryRead.listarTodos();
            setGastosRecurrentes(listGastosRecurrentes);
    };

    const seleccionarGastoRecurrente = (gastoRecurrente?: GastoRecurrente) => {
        setGastoRecurrenteSeleccionado(gastoRecurrente);
    }

    // -----------------
    // PLANTILLAS GASTOS
    const [plantillasGastos, setPlantillasGastos] = useState<PlantillaGasto[]>([]);
    const [plantillaGastoSeleccionada, setPlantillaGastoSeleccionada] = useState<PlantillaGasto>();

    const cargarPlantillasGastos = async () => {
            const listPlantillaGasto = await plantillaGastoApiQueryRead.listarTodos();
            setPlantillasGastos(listPlantillaGasto);
    };

    const seleccionarPlantillaGasto = (plantillaGasto?: PlantillaGasto) => {
        setPlantillaGastoSeleccionada(plantillaGasto);
    }


    // -----------------
    // CALCULADORA
    const [sidebarMode, setSidebarMode] = useState<SidebarMode>(SIDEBAR.FILTER);

    const abrirCalculadora = () => {
        setSidebarMode(SIDEBAR.CALCULADORA);
    }

    const cerrarCalculadora = () => {
        setSidebarMode(SIDEBAR.FILTER);

        setGastoSeleccionado(undefined);
        setCategoriaGastoSeleccionada(undefined);
        setGastoRecurrenteSeleccionado(undefined);
        setPlantillaGastoSeleccionada(undefined);
    }
    // ------------------

    return (
        <GastosContext.Provider
            value={{
                gastos,
                cargarGastos,
                seleccionarGasto,
                gastoSeleccionado,

                categoriasGastos,
                cargarCategoriasGastos,
                categoriaGastoSeleccionada,
                seleccionarCategoriaGasto,

                gastosRecurrentes,
                cargarGastosRecurrentes,
                gastoRecurrenteSeleccionado,
                seleccionarGastoRecurrente,

                plantillasGastos,
                cargarPlantillasGastos,
                plantillaGastoSeleccionada,
                seleccionarPlantillaGasto,

                sidebarMode,

                abrirCalculadora,
                cerrarCalculadora
            }}
        >
            {children}
        </GastosContext.Provider>
    );
}