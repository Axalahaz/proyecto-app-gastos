import { useRef } from "react";

import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";

import { CategoriasCard } from "@/modules/gastos/components/CategoriasCard";
import type { ThemeProps } from "@/shared/theme/themes";
import type { CategoriaGasto } from "../../dto/categoria/CategoriaGasto";

interface Props extends ThemeProps {
    categoriasGastos: CategoriaGasto[];
    onClick: (categoria: CategoriaGasto) => void;
}

export const ModuleCategoria = ({
    categoriasGastos,
    onClick,
    theme,
}: Props) => {

    const categoriasRef = useRef<HTMLDivElement>(null);

    return (
        <div className="flex items-center justify-center py-1 gap-5">
        {/* Flecha izquierda */}
            <button
                onClick={() => {
                    categoriasRef.current?.scrollBy({
                        left: -categoriasRef.current.clientWidth,
                        behavior: "smooth",
                    });
                }}
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
        {/* Lista */}
            <div 
                ref={categoriasRef}
                className="
                min-w-0
                flex overflow-x-auto no-scrollbar 
                snap-x snap-mandatory scroll-smooth"
            >
                {categoriasGastos.map((categoria) => (
                    <button 
                        key={`categoria-${categoria.id}`}
                        className="shrink-0 px-3 first:ps-1"
                        onClick={() => onClick(categoria)}
                    >
                        <CategoriasCard categoria={categoria}/>
                    </button>
                ))}
            </div>
        {/* Flecha derecha */}
            <button
                onClick={() => {
                    categoriasRef.current?.scrollBy({
                        left: categoriasRef.current.clientWidth,
                        behavior: "smooth",
                    });
                }}
                className="
                    rounded-full shadow-md p-1
                    flex items-center justify-center
                    transition-colors hover:bg-[var(--hover-color)]
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
        </div>
    );

} 