import { useState, useMemo, useEffect, useRef } from "react";
import { useOutletContext } from "react-router-dom";

import { useFilter } from "@/hooks/useFilter";
import { useFiltrarGastos } from "@/hooks/useFiltrarGastos"
import { useAcumuladoGastos } from "@/hooks/useAcumuladoGastos"

import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import EditIcon from '@mui/icons-material/Edit';
import AutoStoriesOutlinedIcon from '@mui/icons-material/AutoStoriesOutlined';
import AutoAwesomeOutlinedIcon from '@mui/icons-material/AutoAwesomeOutlined';
import PaymentsOutlinedIcon from '@mui/icons-material/PaymentsOutlined';

import FilterAltIcon from '@mui/icons-material/FilterAlt';
import FilterAltOffIcon from '@mui/icons-material/FilterAltOff';

import { MoneyCard } from "@/modules/gastos/components/MoneyCard";
import { TablaMoney } from "@/shared/components/TablaMoney";

import type { Theme } from "@/shared/theme/themes";

import type {CategoriaGasto} from "@/modules/gastos/dto/categoria/CategoriaGasto";

import { useGastos } from "@/modules/gastos/context/useGastos";
import { useGastosRecurrentesOrdenados } from "@/modules/gastos/hooks/useGastosRecurrentesOrdenados";
import { useEstadoGastosRecurrentes } from "@/modules/gastos/hooks/useEstadoGastosRecurrentes";

import type { GastoRecurrenteVencimiento } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteVencimiento";
import type { GastoRecurrenteViewModel } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteViewModel";
import { RadioButtonsGroup } from "../components/FiltroEstadoMovimiento";
import { ESTADO_MOVIMIENTO, type estadoMovimiento } from "@/shared/types/typesApp";
import { ModuleCategoria } from "../components/ModuleCategoriaGasto/ModuleCategoria";
import { ModuleGastoRecurrente } from "../components/ModuleGastoRecurrente/ModuleGastoRecurrente";

import { ModalCalculadora } from "../components/ModuleCalculadora/ModalCalculadora";
import { FloatingAddGastos } from "../components/FloatingAddGastos";
import { CheckboxBoton } from "@/shared/components/CheckboxBoton";

export const GastosPage = () => {
    // ---------------
    // THEME
    // * Trae el theme desde el contexto guardo para el outlet desde layout 
    const { theme } = useOutletContext<{ theme: Theme }>();

    // ---------------
    // API CONTEXT
    const gastos = useGastos();

    useEffect(() => {
        gastos.cargarGastos();
        gastos.cargarCategoriasGastos();
        gastos.cargarGastosRecurrentes();
        gastos.cargarPlantillasGastos();
    }, []);

    // ---------------
    // GASTO ACUMULADO

    // * Filtrado por tiempo
    const { active, selectedDate} = useFilter();
    const gastosFiltrados = useFiltrarGastos(gastos.gastos, active, selectedDate);
    const gastosAcumulado = useAcumuladoGastos(gastosFiltrados);

    // ---------------
    // GASTOS RECURRENTES
    // * Identitifica que hay scroll horizontal en gastos recurrentes

    const gastosRecurrentesActivos = useMemo(
        () => gastos.gastosRecurrentes.filter(gr => gr.activo),
        [gastos.gastosRecurrentes]
    );

    const gastosRecurrentesActivosOrdenados: GastoRecurrenteVencimiento[] = useGastosRecurrentesOrdenados(gastosRecurrentesActivos);
    const gastosRecurrentesConEstado: GastoRecurrenteViewModel[] = useEstadoGastosRecurrentes(gastosRecurrentesActivosOrdenados);

    // ---------------
    // CATEGORIAS

    // * Filtrar lista
    const [activeGastosPorCategoria, setActiveGastosPorCategoria] = useState(false); 

    const handleCategoriaClick = (categoria: CategoriaGasto) => {
        gastos.seleccionarCategoriaGasto(categoria);

        if (seleccionandoCategoria) {
            setSeleccionandoCategoria(false);
            gastos.abrirCalculadora();
            return;
        }

        setActiveGastosPorCategoria(true);
    };

    // ---------------
    // ULTIMOS GASTOS

    // * Filtrar por estado
    const [checkFilterEstadoMovimiento, setCheckFilterEstadoMovimiento] = useState(false);
    
    // * Filtrar por Estado
    const [filtroEstado, setFiltroEstado] = useState<estadoMovimiento | "TODOS">( 
        ESTADO_MOVIMIENTO.ACTIVO               
    );
    
    // * Datos a mostrar
    const gastosAMostrar = activeGastosPorCategoria
        ?  gastosFiltrados.filter(
            g => g.categoriaGastoId === gastos.categoriaGastoSeleccionada?.id)
        : gastosFiltrados;
    

    // ---------------
    // NUEVO GASTO
    const [seleccionandoCategoria, setSeleccionandoCategoria] = useState(false);
    const handleNuevoGastoClick = () => {
        gastos.prepararNuevoGasto();
        setSeleccionandoCategoria(true);
    };

    // * Efecto resaltado transparente negro
    const categoriasRef = useRef<HTMLElement | null>(null);
    useEffect(() => {
        if (!seleccionandoCategoria) return;

        categoriasRef.current?.scrollIntoView({
            behavior: "smooth",
            block: "center",
        });

        const handleClickOutside = (event: MouseEvent) => {
            if (
                categoriasRef.current &&
                !categoriasRef.current.contains(event.target as Node)
            ) {
                setSeleccionandoCategoria(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);

        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, [seleccionandoCategoria]);

    // ------------------------
    return (
        <div className="h-dvh pb-5 px-5 flex flex-col gap-5">
{/* Contenedor de Acumulado */}
            <section className="flex justify-center -me-5">
                <MoneyCard 
                    amount={gastosAcumulado}
                    typeMoney="Pesos argentinos"
                    subtitle="ACUMULADOS"
                    theme={theme}
                />
            </section>
{/* Contenedor Gastos Fijos */}
            <section className="flex flex-col gap-2">
                <div className="flex items-center justify-between">
                    <div className="flex items-center gap-2">
                        <AutoAwesomeOutlinedIcon
                            sx={{
                                fontSize: 20,
                                color: theme.colors[700],
                            }}
                        />
                        <h1 className="text-sm">GASTOS FIJOS</h1>
                    </div>
                    <div className="flex gap-5">
                        <button className="flex items-center gap-1">
                            <EditIcon
                                sx={{
                                    fontSize: 16,
                                    color: theme.colors[700],
                                }}
                            />
                            <span className="text-xs">Editar</span>
                        </button>
                        <button className="flex items-center gap-1">
                            <AddOutlinedIcon
                                sx={{
                                    fontSize: 16,
                                    color: theme.colors[700],
                                }}
                            />
                            <span className="text-xs">Agregar</span>
                        </button>
                    </div>
                </div>
                <ModuleGastoRecurrente
                    gastosRecurrentesConEstado={gastosRecurrentesConEstado}
                    theme={theme}
                />
            </section>
{/* Contenedor de Categorias */}
            <section 
                ref={categoriasRef}
                className={`flex flex-col z-50 
                    ${seleccionandoCategoria && 
                        `-m-2 p-2 transition-all duration-300 rounded-[10px] `
                    }
                `}
                style={{
                    backgroundColor: seleccionandoCategoria
                        ? `${theme.colors[100]}`
                        : "transparent",
                    boxShadow: seleccionandoCategoria
                        ? `0 0 10px 2px ${theme.colors[300]}`
                        : "none",
                }}
            >
                <div className="flex items-center justify-between">
                    <div className="flex gap-2  items-center">
                        <AutoStoriesOutlinedIcon sx={{fontSize: 20,  color: theme.colors[700],}}  />
                        <h1 className="text-sm">
                            {seleccionandoCategoria
                                ? "SELECCIONÁ UNA CATEGORÍA"
                                : "CATEGORÍAS"
                            }
                        </h1>
                    </div>
                    {!seleccionandoCategoria && (
                        <div className="flex gap-5">
                            <button className="flex items-center gap-1">
                                <EditIcon
                                    sx={{
                                        fontSize: 16,
                                        color: theme.colors[700],
                                    }}
                                />
                                <span className="text-xs">Editar</span>
                            </button>
                            <button className="flex items-center gap-1">
                                <AddOutlinedIcon
                                    sx={{
                                        fontSize: 16,
                                        color: theme.colors[700],
                                    }}
                                />
                                <span className="text-xs">Agregar</span>
                            </button>
                        </div>
                    )}
                </div>
                <ModuleCategoria
                    categoriasGastos = {gastos.categoriasGastos}
                    onClick = {handleCategoriaClick}
                    theme = {theme}
                />
                
            </section>
{/* Contenedor de Tabla de Items */}
            <section className="flex flex-col gap-2 pb-10">
                <div className="flex items-center justify-between">
                    <div className="flex gap-2">
                        <PaymentsOutlinedIcon
                            sx={{
                                fontSize: 20, 
                                color: theme.colors[700],
                            }} 
                        />
                        
                        <h1 className="text-sm ">ULTIMOS GASTOS</h1>
                    </div>
                    {activeGastosPorCategoria && 
                        <h1 
                            className="text-center text-sm text-bold"
                            style={{
                                color: `${theme.colors[500]}`,
                            }}
                        > 
                            {activeGastosPorCategoria
                                && `• ${gastos.categoriaGastoSeleccionada?.nombre.toUpperCase()} •` 
                            }
                        </h1>
                    }
                    <div className="flex font-beiruti gap-3">
                        <button
                            onClick={() => {
                                setActiveGastosPorCategoria(false);
                                gastos.seleccionarCategoriaGasto(null);
                            }}
                            className="
                                border rounded-[10px] px-2
                                active:scale-80
                                transition-all duration-200
                                text-[var(--text-color)]
                                border-[var(--border-color)]
                                hover:bg-[var(--color-hover)]
                                hover:text-[var(--text-hover)]
                                hover:border-[var(--color-hover)]
                            "
                            style={{
                                "--text-color": theme.colors[900],
                                "--border-color": theme.colors[900],
                                "--color-hover": theme.colors[700],
                                "--text-hover": theme.colors[100],
                            } as React.CSSProperties}
                        >
                            VER TODOS
                        </button>
                        <CheckboxBoton  
                            texto="Filtrar por Estado Movimiento"
                            fontSize="1rem"
                            fontFamily="var(--font-beiruti)"
                            esActivo={checkFilterEstadoMovimiento}
                            setEsActivo={setCheckFilterEstadoMovimiento}
                            sizeCheckbox="small"
                            icon={<FilterAltOffIcon />}
                            checkedIcon={<FilterAltIcon />}
                            theme={theme}
                        />
                    </div>
                </div>
                {checkFilterEstadoMovimiento && 
                    <div className="flex">
                        <RadioButtonsGroup 
                            filtroEstado={filtroEstado} 
                            setFiltroEstado={setFiltroEstado} 
                            theme={theme}
                            />
                    </div>
                }
                <TablaMoney
                    onItemFila ={gastos.seleccionarGasto}
                    filtroEstado={
                        checkFilterEstadoMovimiento 
                        ? filtroEstado
                        : ESTADO_MOVIMIENTO.ACTIVO
                    } 
                    items={gastosAMostrar}
                    onEdit={gastos.abrirCalculadora}
                    theme={theme}
                    filaClassName="px-2 border-b-1 rounded-[5px]"
                    className="overflow-y-auto snap-x snap-mandatory scroll-smooth custom-scroll"
                />
            </section>

            {seleccionandoCategoria && (
                <div
                    className="fixed inset-0 z-40 pointer-events-none"
                    style={{
                        backgroundColor: "rgba(0, 0, 0, 0.90)",
                    }}
                />
            )}
            <FloatingAddGastos 
                active={seleccionandoCategoria}
                onClick={handleNuevoGastoClick}
                theme={theme} 
            />

            {gastos.activeCalculadora && 
                <ModalCalculadora 
                    open={gastos.activeCalculadora}
                    onClose={gastos.cerrarCalculadora}
                    theme= {theme}
                />
            }
        </div>
    );
}