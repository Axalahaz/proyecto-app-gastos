import type { ThemeProps } from "@/shared/theme/themes";
import type { GastoRecurrenteViewModel } from "../../dto/gastoRecurrente/GastoRecurrenteViewModel";
import { useRef, useState } from "react";
import { GastosRecurrenteCard } from "../GastosRecurrenteCard";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import { defaultTheme } from "@/shared/theme";

interface Props extends ThemeProps {
    gastosRecurrentesConEstado:  GastoRecurrenteViewModel[];
}

export const ModuleGastoRecurrente = ({
    gastosRecurrentesConEstado,
    theme,
}: Props) => {
    
    const ITEMS_POR_PAGINA = 4;

    const [paginaActual, setPaginaActual] = useState(0);

    const totalPaginas = Math.ceil(gastosRecurrentesConEstado.length / ITEMS_POR_PAGINA);
    
    // * actualizacion de scroll
    const carruselRef = useRef<HTMLDivElement>(null);
    const handleScrollGastosRecurrentes = () => {
        const container = carruselRef.current;
        if (!container) return;

        const maxScroll =
            container.scrollWidth - container.clientWidth;

        if (maxScroll <= 0) {
            setPaginaActual(0);
            return;
        }

        const progreso =
            container.scrollLeft / maxScroll;

        const pagina = Math.round(
            progreso * (totalPaginas - 1)
        );

        setPaginaActual(pagina);
    };

    // ---------------------------
    const scrollPagina = (direccion: "izquierda" | "derecha") => {
        const container = carruselRef.current;
        if (!container) return;

        container.scrollBy({
            left:
                direccion === "derecha"
                    ? container.clientWidth
                    : -container.clientWidth,
            behavior: "smooth",
        });
    };
    
    // ---------------------------
    return (
        <div className="flex flex-col items-center justify-center gap-2">
            <div className="w-full flex items-center justify-center  gap-2">
            {/* Flecha izquierda */}
                {paginaActual > 0 && (
                    <button
                        onClick={() => scrollPagina("izquierda")}
                        className="
                            rounded-full shadow-md p-1
                            flex items-center justify-center
                            transition-colors hover:bg-[var(--hover-color)]
                        "
                        style={{
                            "--hover-color": theme.colors[300],
                        } as React.CSSProperties}
                    >
                        <ChevronLeftIcon
                            sx={{
                                fontSize: 22,
                                color: theme.colors[700],
                            }}
                        />
                    </button>
                )}
            {/* Carrusel */}
                <div
                    ref={carruselRef}
                    onScroll={handleScrollGastosRecurrentes}
                    className="
                    min-w-0 flex py-1
                    overflow-x-auto no-scrollbar 
                    snap-x snap-mandatory scroll-smooth
                    "
                >
                    {gastosRecurrentesConEstado.map((gastoRecurrenteItem) => (
                        <div
                            key={`gastoRecurrente-${gastoRecurrenteItem.gastoRecurrente.id}`}
                            className={`
                                shrink-0 snap-start
                                first:ms-0 
                                flex justify-center
                            `}
                            style={{
                                width: `${100 / ITEMS_POR_PAGINA}%`,
                            }}
                        >
                            <GastosRecurrenteCard
                                gastoRecurrente={gastoRecurrenteItem}
                            />
                        </div>
                    ))}
                </div>
            {/* Flecha derecha */}
                {paginaActual < totalPaginas - 1 && (
                    <button
                        onClick={() => scrollPagina("derecha")}
                        className="
                            rounded-full shadow-md p-1
                            flex items-center justify-center
                            transition-colors 
                            hover:bg-[var(--hover-color)]
                        "
                        style={{
                            "--hover-color": theme.colors[300],
                        } as React.CSSProperties}
                    >
                        <ChevronRightIcon
                            sx={{
                                fontSize: 22,
                                color: theme.colors[700],
                                
                            }}
                        />
                    </button>
                )}
            </div>
        {/* Indicador de páginas */}
            <div className=" py-1 flex justify-center gap-2">
                {Array.from({ length: totalPaginas }).map((_, index) => (
                    <div
                        key={`indiceRecurrente-${index}`}
                        className="w-2 h-2 rounded-full"
                        style={{
                            background:
                                index === paginaActual
                                    ? theme.colors[700]
                                    : defaultTheme.colors[300],
                        }}
                    />
                ))}
            </div>
            {/* //! desarrollo
            <div className="flex justify-center">
                páginas: {totalPaginas} | actual: {paginaActual}
            </div>
            */}
        </div>
    );
}