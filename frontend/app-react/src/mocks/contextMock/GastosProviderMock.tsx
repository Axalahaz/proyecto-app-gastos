import { useCallback, useState } from "react";
import type { ReactNode } from "react";

import { GastosContext } from "@/modules/gastos/context/GastosContext";

import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";
import { gastosMock } from "@/mocks/GastosMock";
import { categoriasGastoMock } from "@/mocks/CategoriasMock";
import { gastosRecurrentesMock } from "@/mocks/GastosRecurrentesMock";
import { plantillasGastoMock } from "@/mocks/PlantillasMock";

interface Props {
    children: ReactNode;
}

export const GastosProviderMock = ({
    children
}: Props) => {

    // -----------------
    // GASTOS
    const [gastos, setGastos] = useState<Gasto[]>([]);
    const [gastoSeleccionado, setGastoSeleccionado] = useState<Gasto | null>(null);

    const cargarGastos = async () => {
            setGastos(gastosMock);
    };

    const seleccionarGasto = useCallback((gasto: Gasto | null) => {
        setGastoSeleccionado(gasto);
    },[]);
    
    const modificarGasto = (gasto: Gasto) => {
        setGastos(prev =>
            prev.map(g =>
                g.id === gasto.id
                    ? gasto
                    : g
            )
        );
        setGastoSeleccionado(gasto);
    }

    const registrarGasto = (gasto: Gasto) => {
        setGastos(prev => [...prev, gasto]);
    };

    const eliminarGasto = (gasto: Gasto) => {
        const id = gasto.id;
        setGastos(prev =>
            prev.filter(gasto => gasto.id !== id)
        );

        // Si el gasto eliminado estaba seleccionado,
        // limpiamos la selección.
        if (gastoSeleccionado?.id === id) {
            setGastoSeleccionado(null);
        }
    };

    // -----------------
    // CATEGORIA GASTO
    const [categoriasGastos, setCategoriasGastos] = useState<CategoriaGasto[]>([]);
    const [categoriaGastoSeleccionada, setCategoriaGastoSeleccionada] = useState<CategoriaGasto | null>(null);

    const cargarCategoriasGastos = async () => {
            setCategoriasGastos(categoriasGastoMock);
    };

    const seleccionarCategoriaGasto = useCallback((categoriaGasto: CategoriaGasto | null) => {
        setCategoriaGastoSeleccionada(categoriaGasto);
    },[]);

    const modificarCategoriaGasto = (categoriaGasto: CategoriaGasto) => {
        setCategoriasGastos(prev =>
            prev.map(g =>
                g.id === categoriaGasto.id
                    ? categoriaGasto
                    : g
            )
        );
        setCategoriaGastoSeleccionada(categoriaGasto);
    }

    const registrarCategoriaGasto = (categoriaGasto: CategoriaGasto) => {
        setCategoriasGastos(prev => [...prev, categoriaGasto]);
    };

    const eliminarCategoriaGasto = (categoriaGasto: CategoriaGasto) => {
        const id = categoriaGasto.id;
        setCategoriasGastos(prev =>
            prev.filter(categoriaGasto => categoriaGasto.id !== id)
        );

        if (categoriaGastoSeleccionada?.id === id) {
            setCategoriaGastoSeleccionada(null);
        }
    };

    // -----------------
    // GASTOS RECURRENTES
    const [gastosRecurrentes, setGastosRecurrentes] = useState<GastoRecurrente[]>([]);
    const [gastoRecurrenteSeleccionado, setGastoRecurrenteSeleccionado] = useState<GastoRecurrente | null>(null);

    const cargarGastosRecurrentes = async () => {
            setGastosRecurrentes(gastosRecurrentesMock);
    };
    
    const seleccionarGastoRecurrente = useCallback((gastoRecurrente: GastoRecurrente | null) => {
        setGastoRecurrenteSeleccionado(gastoRecurrente);
    },[]);

    const modificarGastoRecurrente = (gastoRecurrente: GastoRecurrente) => {
        setGastosRecurrentes(prev =>
            prev.map(g =>
                g.id === gastoRecurrente.id
                    ? gastoRecurrente
                    : g
            )
        );
        setGastoRecurrenteSeleccionado(gastoRecurrente);
    }

    const registrarGastoRecurrente = (gastoRecurrente: GastoRecurrente) => {
        setGastosRecurrentes(prev => [...prev, gastoRecurrente]);
    };

    const eliminarGastoRecurrente = (gastoRecurrente: GastoRecurrente) => {
        const id = gastoRecurrente.id;
        setGastosRecurrentes(prev =>
            prev.filter(gastoRecurrente => gastoRecurrente.id !== id)
        );

        if (gastoRecurrenteSeleccionado?.id === id) {
            setGastoRecurrenteSeleccionado(null);
        }
    };

    // -----------------
    // PLANTILLAS GASTOS
    const [plantillasGastos, setPlantillasGastos] = useState<PlantillaGasto[]>([]);
    const [plantillaGastoSeleccionada, setPlantillaGastoSeleccionada] = useState<PlantillaGasto | null>(null);

    const cargarPlantillasGastos = async () => {
            setPlantillasGastos(plantillasGastoMock);
    };

    const seleccionarPlantillaGasto = useCallback((plantillaGasto: PlantillaGasto | null) => {
        setPlantillaGastoSeleccionada(plantillaGasto);
    },[]);
    
    const modificarPlantillaGasto = (plantillaGasto: PlantillaGasto) => {
        setPlantillasGastos(prev =>
            prev.map(g =>
                g.id === plantillaGasto.id
                    ? plantillaGasto
                    : g
            )
        );
        setPlantillaGastoSeleccionada(plantillaGasto);
    }

    const registrarPlantillaGasto = (plantillaGasto: PlantillaGasto) => {
        setPlantillasGastos(prev => [...prev, plantillaGasto]);
    };

    const eliminarPlantillaGasto = (plantillaGasto: PlantillaGasto) => {
        const id = plantillaGasto.id;
        setPlantillasGastos(prev =>
            prev.filter(plantillaGasto => plantillaGasto.id !== id)
        );

        if (plantillaGastoSeleccionada?.id === id) {
            setPlantillaGastoSeleccionada(null);
        }
    };

    // -----------------
    // CALCULADORA
    const [activeCalculadora, setActiveCalculadora] = useState(false);

    const abrirCalculadora = () => {
        setActiveCalculadora(true);
    }

    const prepararNuevoGasto  = () => {
        setGastoSeleccionado(null);
        setCategoriaGastoSeleccionada(null);
        setGastoRecurrenteSeleccionado(null);
        setPlantillaGastoSeleccionada(null);
    };

    const cerrarCalculadora = () => {
        setActiveCalculadora(false);

        setGastoSeleccionado(null);
        setCategoriaGastoSeleccionada(null);
        setGastoRecurrenteSeleccionado(null);
        setPlantillaGastoSeleccionada(null);
    }
    // ------------------


    return (
        <GastosContext.Provider
            value={{
                gastos,
                cargarGastos,
                seleccionarGasto,
                gastoSeleccionado,
                modificarGasto,
                registrarGasto,
                eliminarGasto,

                categoriasGastos,
                cargarCategoriasGastos,
                categoriaGastoSeleccionada,
                seleccionarCategoriaGasto,
                modificarCategoriaGasto,
                registrarCategoriaGasto,
                eliminarCategoriaGasto,

                gastosRecurrentes,
                cargarGastosRecurrentes,
                gastoRecurrenteSeleccionado,
                seleccionarGastoRecurrente,
                modificarGastoRecurrente,
                registrarGastoRecurrente,
                eliminarGastoRecurrente,

                plantillasGastos,
                cargarPlantillasGastos,
                plantillaGastoSeleccionada,
                seleccionarPlantillaGasto,
                modificarPlantillaGasto,
                registrarPlantillaGasto,
                eliminarPlantillaGasto,

                activeCalculadora,
                abrirCalculadora,
                prepararNuevoGasto,
                cerrarCalculadora
            }}
        >
            {children}
        </GastosContext.Provider>
    );
}