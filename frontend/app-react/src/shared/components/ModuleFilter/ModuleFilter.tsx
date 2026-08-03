import { useFilter } from "@/hooks/useFilter";
import type { FilterType } from "@/context/typesFilter";
import { FILTERS } from "@/context/typesFilter";

import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme/defaultTheme";

import { FilterDateSelector } from "@/shared/components/ModuleFilter/FilterDateSelector"

export const ModuleFilter = ({
    theme,
}: ThemeProps) => {

    // ---------------
    // Obtengo el filtro de Fecha
    const { active, setActive } = useFilter();

    // ---------------
    // Establesco referencias y comportamientos de los estilos
    const buttons = [
        FILTERS.MENSUAL,
        FILTERS.DIARIO,
        FILTERS.ANUAL,
        FILTERS.PERIODO,
    ] as const;

    const getStyles = (button: FilterType) => ({
        borderColor: defaultTheme.colors[100],
        backgroundColor: active === button ? theme.colors[700] : "transparent",
        color: active === button ? theme.colors[100] : defaultTheme.colors[700] ,
    });

    const getScale = (button: FilterType) =>
        active === button 
            ? "w-[40dvh] rounded-[30px] text-lg" 
            : "w-[35dvh] text-sm ";
    // ---------------

    return (
        <div className={`w-full py-2 flex flex-col items-center gap-2 `}>
{/* BOTONES */}
                {buttons.map((b) => (
                    <div className={`w-full flex justify-center`}>
                        <button 
                            key={b.value}
                            onClick={() => setActive(b.value)}
                            className={`${getScale(b.value)} 
                            active:scale-120 border-t border-b py-1 transition-all`}
                            style={{ ...getStyles(b.value) }}
                        >
                            {b.label}
                        </button>
                    </div>
                ))}

{/* CALENDARIO */}
            <div className="">
                <FilterDateSelector 
                    filter={active}
                    theme={theme} 
                />
            </div>
        </div>
    );
};