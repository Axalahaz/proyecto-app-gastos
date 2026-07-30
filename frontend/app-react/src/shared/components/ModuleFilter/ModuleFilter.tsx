import { useFilter } from "@/hooks/useFilter";
import type { FilterType } from "@/context/typesFilter";
import { FILTERS } from "@/context/typesFilter";

import type { ThemeProps } from "@/shared/theme/themes";

import { FilterDateSelector } from "@/shared/components/ModuleFilter/FilterDateSelector"

export const ModuleFilter = ({
    theme,
}: ThemeProps) => {
    // ---------------
    // Obtengo el filtro de Fecha
    const { active, setActive } = useFilter();

    // ---------------
    // Establesco referencias y comportamientos de los estilos
    const buttonMensual = FILTERS.MENSUAL;
    const buttons = [
        {
            filter: FILTERS.DIARIO,
            z: 3,
            className: "px-5",
        },
        {
            filter: FILTERS.ANUAL,
            z: 2,
            className: "-ml-4 ps-6 pe-4",
        },
        {
            filter: FILTERS.PERIODO,
            z: 1,
            className: "-ml-4 ps-6 pe-4",
        },
    ] as const;

    const getStyles = (button: FilterType) => ({
        borderColor: theme.colors[300],
        backgroundColor: active === button ? theme.colors[700] : "white",
        color: active === button ? "white" : theme.colors[700],
    });

    const getScale = (button: FilterType) =>
        active === button ? "active:scale-120 pb-2 active:rounded-b-[15px]" : "pb-2";
    // ---------------

    return (
        <div className={`flex flex-col gap-1`}>
{/* BOTONES */}
            <div className= {`flex items-center justify-between px-1 relative z-10`}>
        {/* BOTON DIARIO */}
                <div className="flex">
                    <button 
                        key={buttonMensual.value}
                        onClick={() => setActive(buttonMensual.value)}
                        className={`${getScale(buttonMensual.value)}
                            text-sm border rounded-t-[15px] px-3 transition-all`}
                        style={getStyles(buttonMensual.value)}
                    >
                        {buttonMensual.label}
                    </button>
                </div>
        {/* RESTO DE BOTONES */}
                <div className="flex">
                    {buttons.map(({filter: filter, z, className}) => (
                        <button 
                            key={filter.value}
                            onClick={() => setActive(filter.value)}
                            className={`${getScale(filter.value)} ${className} 
                            text-sm border rounded-t-[15px] transition-all`}
                            style={{ zIndex: z, ...getStyles(filter.value) }}
                        >
                            {filter.label}
                        </button>
                    ))}
                </div>
            </div>

{/* CALENDARIO */}
            <div className="-mt-3 relative z-20">
                <FilterDateSelector theme={theme} />
            </div>
        </div>
    );
};