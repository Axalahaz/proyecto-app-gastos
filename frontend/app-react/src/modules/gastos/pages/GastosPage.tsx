import { useState, useEffect, useRef, useMemo } from "react";
import { useNavigate, useOutletContext } from "react-router-dom";

import { useFilter } from "@/hooks/useFilter";
import { useFiltrarGastos } from "@/hooks/useFiltrarGastos"
import { useAcumuladoGastos } from "@/hooks/useAcumuladoGastos"

import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';
import EditIcon from '@mui/icons-material/Edit';
import BlurOnOutlinedIcon from '@mui/icons-material/BlurOnOutlined';
import PaymentsOutlinedIcon from '@mui/icons-material/PaymentsOutlined';
import ToggleOnOutlinedIcon from '@mui/icons-material/ToggleOnOutlined';
import ToggleOffOutlinedIcon from '@mui/icons-material/ToggleOffOutlined';

import { MoneyCard } from "@/shared/components/MoneyCard";
import { TablaMoney } from "@/shared/components/TablaMoney";
import { SIDEBAR } from "@/shared/types/typeSidebar";

import { GastosRecurrenteCard } from "@/modules/gastos/components/GastosRecurrenteCard";
import { CategoriasCard } from "@/modules/gastos/components/CategoriasCard";

import type { Theme } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme";

import type {CategoriaGasto} from "@/modules/gastos/dto/categoria/CategoriaGasto";

import { useGastos } from "@/modules/gastos/context/useGastos";
import { useGastosRecurrentesOrdenados } from "@/modules/gastos/hooks/useGastosRecurrentesOrdenados";
import { useEstadoGastosRecurrentes } from "@/modules/gastos/hooks/useEstadoGastosRecurrentes";

import type { GastoRecurrenteVencimiento } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteVencimiento";
import type { GastoRecurrenteViewModel } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteViewModel";



export const GastosPage = () => {
    console.log("---gastosPage---") //!!
    
    // ---------------
    // THEME
    // * Trae el theme desde el contexto guardo para el outlet desde layout 
    const { theme } = useOutletContext<{ theme: Theme }>();

    // ---------------
    // API CONTEXT
    const {
        gastos, 
        cargarGastos,
        categoriasGastos,
        cargarCategoriasGastos,
        gastosRecurrentes,
        cargarGastosRecurrentes,
        abrirCalculadora,
        sidebarMode,
        cerrarCalculadora
    } = useGastos();

    useEffect(() => {
        cargarGastos();
        cargarCategoriasGastos();
        cargarGastosRecurrentes();
    }, []);

    console.log("---lista gastos: ", gastos); // !!!!!!

    // * Filtrado por tiempo
    const { active, selectedDate} = useFilter();
    const gastosFiltrados = useFiltrarGastos(gastos, active, selectedDate);
    const gastosAcumulado = useAcumuladoGastos(gastosFiltrados);
    
    // ---------------
    // GASTOS RECURRENTES
    // * Identitifica que hay scroll horizontal en gastos recurrentes

    const gastosRecurrentesActivos = useMemo(
        () => gastosRecurrentes.filter(gr => gr.activo),
        [gastosRecurrentes]
    );

    const gastosRecurrentesActivosOrdenados: GastoRecurrenteVencimiento[] = useGastosRecurrentesOrdenados(gastosRecurrentesActivos);
    const gastosRecurrentesConEstado: GastoRecurrenteViewModel[] = useEstadoGastosRecurrentes(gastosRecurrentesActivosOrdenados);

    const [paginaActual, setPaginaActual] = useState(0);
    const totalPaginas = Math.ceil(gastosRecurrentesConEstado.length / 3);

    // * actualizacion de scroll
    const carruselRef = useRef<HTMLDivElement>(null);
    const handleScroll = () => {
        const container = carruselRef.current;
        if (!container) return;

        const maxScroll =
            container.scrollWidth - container.clientWidth;

        const progreso =
            container.scrollLeft / maxScroll;

        const pagina = Math.round(
            progreso * (totalPaginas - 1)
        );

        setPaginaActual(pagina);
    };

    // ---------------
    // Section Tabla
    // * Acciones para el filtrado de la tabla de gastos por categoria
    const [selectCategoria, setSelectCategoria] = useState<CategoriaGasto>();
    
    const gastosAMostrar = sidebarMode === SIDEBAR.FILTER
        ? selectCategoria && gastosFiltrados.filter(
            gasto => gasto.categoriaGastoId === selectCategoria.id
        )
        : gastosFiltrados;

    // ---------------
    // Permite navegar hacia una ruta determinada
    const navigate = useNavigate();
    const sumarGasto = (categoria: CategoriaGasto) => {
        navigate("/calculadora/gastos", {
            state: {
                categoria,
            },
        });
    };

    const handleCategoriaClick = (categoria: CategoriaGasto) => {
        setSelectCategoria(categoria);
        sidebarMode === SIDEBAR.CALCULADORA && sumarGasto(categoria);
    };

    const handleSidebardClick = () => {
        sidebarMode === SIDEBAR.FILTER
            ? abrirCalculadora()
            : cerrarCalculadora()
    }
    
    console.log("------fitlrado sidebar:",sidebarMode)

    return (
        <div className="h-full flex flex-col gap-5">
{/* Contenedor de Acumulado */}
            <section className="">
                <MoneyCard 
                    amount={gastosAcumulado}
                    typeMoney="(Pesos argentinos)"
                    subtitle="ACUMULADOS"
                    theme={theme}
                    subtitleClassName="px-4 rounded-[50px]"
                />
            </section>
{/* Contenedor Gastos Fijos */}
            <section className="flex flex-col gap-2">
                <div className="flex justify-between">
                    <div className="flex items-center gap-2">
                        <AutoAwesomeIcon sx={{fontSize: 15, color: theme.colors[700]}}/>
                        <h1 className="text-sm">GASTOS FIJOS</h1>
                    </div>
                    <button className="flex items-center gap-1 ">
                        <span className="text-xs">Editar</span>
                        <EditIcon 
                            sx={{
                                fontSize: 16,
                                color: theme.colors[700],
                            }}
                        />
                    </button>
                </div>

                <div
                    ref={carruselRef}
                    onScroll={handleScroll} 
                    className="flex
                    overflow-x-auto no-scrollbar snap-x snap-mandatory scroll-smooth"
                >
                    {gastosRecurrentesConEstado.map((gastoRecurrenteItem) => (
                        <div 
                            key={`gastoRecurrente-${gastoRecurrenteItem.gastoRecurrente.id}`}
                            className="px-1 w-1/3 shrink-0 snap-start first:ms-0 flex"
                        >
                            <GastosRecurrenteCard gastoRecurrente={gastoRecurrenteItem} />
                        </div>
                    ))}
                </div>
                
                <div className="flex justify-center gap-2"  >
                    {Array.from({ length: totalPaginas }).map((_, index) => (
                        <div
                            key={`indiceRecurrente-${index}`}
                            className={`w-2 h-2 rounded-full`}
                            style={{
                                background: `${
                                    index === paginaActual
                                    ? theme.colors[700]
                                    : defaultTheme.colors[300]
                                }`
                            }}
                        />
                    ))}
                </div>
            </section>
{/* Contenedor de Categorias */}
            <section className="flex flex-col -mt-2 gap-2">
                <div className="flex justify-between">
                    <div className="flex gap-2  items-center">
                        <PaymentsOutlinedIcon
                            sx={{
                                fontSize: 20, 
                                color: theme.colors[700],
                            }} 
                        />
                        <h1 className="text-sm">
                        {sidebarMode === SIDEBAR.CALCULADORA 
                            && "AGREGAR POR CATEGORIA"
                            || "SELECCIONAR CATEGORIA"
                        }
                        </h1>
                    </div>
                    <button className="flex items-center gap-1 ">
                        <span className="text-xs">Editar</span>
                        <EditIcon 
                            sx={{
                                fontSize: 16,
                                color: theme.colors[700],
                            }}
                        />
                    </button>
                </div>

                <div className="flex  overflow-x-auto no-scrollbar 
                snap-x snap-mandatory scroll-smooth"
                >
                    {categoriasGastos.map((categoria) => (
                        <button 
                            key={`categoria-${categoria.id}`}
                            className="shrink-0 px-2 first:ps-1"
                            onClick={() => handleCategoriaClick(categoria)}
                        >
                            <CategoriasCard categoria={categoria}/>
                        </button>
                    ))}
                </div>
            </section>
{/* Contenedor de Tabla de Items */}
            <section className="flex flex-col justify-center min-h-0 gap-1">
                <div className="flex items-center justify-between text-sm">
                    <div className="flex-[2] flex items-center justify-end gap-1">
                        <BlurOnOutlinedIcon
                            sx={{
                                fontSize: sidebarMode === SIDEBAR.CALCULADORA ? 20 : 15, 
                                color: sidebarMode === SIDEBAR.CALCULADORA ? theme.colors[700] : theme.colors[300],
                            }} 
                        /> 
                        <h1>Últimos gastos</h1>
                    </div>
                    <button 
                        onClick={() => handleSidebardClick()}
                        className= "flex-1 gap-1 active:scale-80"
                    >
                    {sidebarMode === SIDEBAR.FILTER
                        && <ToggleOnOutlinedIcon
                                sx={{
                                    fontSize: 20,
                                    color: theme.colors[700],
                                }}
                            />
                        || <ToggleOffOutlinedIcon
                                sx={{
                                    fontSize: 20,
                                    color: theme.colors[700],
                                }}
                            />
                        }          
                    </button>
                    <div className="flex-[2] flex items-center justify-start gap-1">
                        <h1>Por Categoria</h1>
                        <BlurOnOutlinedIcon
                            sx={{
                                fontSize: sidebarMode === SIDEBAR.FILTER ? 20 : 15,  
                                color: sidebarMode === SIDEBAR.FILTER ? theme.colors[700] : theme.colors[300],
                            }} 
                        /> 
                    </div>
                </div>
                <div>
                    <h1 
                        className="text-center text-xs text-bold"
                        style={{
                            color: `${theme.colors[500]}`,
                        }}
                    > 
                        {sidebarMode === SIDEBAR.FILTER 
                            ? (selectCategoria 
                                ? `• ${selectCategoria?.nombre.toUpperCase()} •` 
                                : "• Seleccionar categoria •") 
                            : ""
                        } 
                    </h1>
                </div>
                <TablaMoney
                    items={gastosAMostrar}
                    emptyMessage="No hay gastos en esta categoría"
                    theme={theme}
                    filaClassName="px-2 border-b-1 rounded-[5px]"
                    className="overflow-y-auto snap-x snap-mandatory scroll-smooth custom-scroll"
                />
            </section>

        </div>
    );
}