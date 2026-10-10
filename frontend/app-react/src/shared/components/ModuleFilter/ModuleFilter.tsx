import { useState } from "react";

import { useFilter } from "@/hooks/useFilter";
import type { FilterType } from "@/context/typesFilter";
import { FILTERS } from "@/context/typesFilter";

import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import { ModalCalendario } from "@/shared/components/ModuleFilter/ModalCalendario";
import { filtrarFechaFormat } from "@/shared/utils/filtrarFechaFormat";

import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme";

export const ModuleFilter = ({
    theme,
}: ThemeProps) => {

    // ---------------
    // Obtengo el filtro de Fecha
    const { active, setActive, selectedDate} = useFilter();

    // ---------------
    // MODAL CALENDARIO
    const [openCalendario, setOpenCalendario] = useState(false);

    // ---------------
    // Establesco referencias y comportamientos de los estilos
    const buttons = [
        FILTERS.MENSUAL,
        FILTERS.DIARIO,
        FILTERS.ANUAL,
    ] as const;

    const getStyles = (button: FilterType) => ({
        borderColor: active === button ? theme.colors[500] : defaultTheme.colors[100],
        backgroundColor: active === button ? `${theme.colors[700]}` : "transparent",
        color: active === button ? theme.colors[100] : defaultTheme.colors[700] ,
    });

    const getScale = (button: FilterType) =>
        active === button 
            ? "w-[10vw] text-base rounded-t-[15px] border-t" 
            : "w-[8vw] text-sm mt-1";
    // ---------------
    return (
        <div 
            className={`ms-5 pt-2 flex justify-center border-l`}
            style={{
                backgroundColor: theme.colors[100],
                borderColor: `${theme.colors[500]}50`,
            }}
        >
{/* BOTONES */}
            <div className="flex-1 flex items-center justify-center">
                {buttons.map((b) => (
                    <div 
                        key={b.value}
                        className={`flex justify-center`}
                    >
                        <button 
                            onClick={() => setActive(b.value)}
                            className={`${getScale(b.value)} active:scale-80  transition-all`}
                            style={{ ...getStyles(b.value) }}
                        >
                            {b.label}
                        </button>
                    </div>
                ))}
            </div>
{/* CALENDARIO */}
            <div className="flex-1 flex justify-center">
                <button 
                    className="flex items-center gap-2"
                    onClick={() => setOpenCalendario(true)}
                >
                    <CalendarMonthIcon sx={{fontSize: 25, color: theme.colors[700]}}/> 
                    <p className="text-sm">{filtrarFechaFormat(active, selectedDate)}</p>
                    <ArrowDropDownIcon sx={{fontSize: 25, color: theme.colors[700]}}/>
                </button>
        
                <div className="">
                    <ModalCalendario 
                        filter = {active}
                        open={openCalendario}
                        onClose={() => setOpenCalendario(false)}
                        theme={theme}
                    />
                </div>
            </div>
        </div>
    );
};