import { useState } from "react";

import { useFilter } from "@/hooks/useFilter";

import { filtrarFechaFormat } from "@/shared/utils/filtrarFechaFormat";
import type { ThemeProps } from "@/shared/theme/themes";

import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';

import { DatePicker } from "@mui/x-date-pickers/DatePicker";

import type { Dayjs } from "dayjs";
import "dayjs/locale/es";


// !! --------------------------
// !! El calendario no es valido para el filtro: Periodo
// !! --------------------------

export const FilterDateSelector = ({
    theme
}: ThemeProps) => {
    // --------------
    // Obtengo fecha del filtro
    const { active, selectedDate, setSelectedDate } = useFilter();

    // --------------
    // Calendario
    const [open, setOpen] = useState(false);
    
    const cambioFecha = (newDate: Dayjs | null) => {
        if (!newDate) return;
        setSelectedDate(newDate);
        setOpen(false);
    };

    return (
        <div className="flex flex-col px-5 pt-2
        border-t border-r border-l rounded-t-[20px]"
        style={{
            background: theme.colors[100],
            borderColor: `${theme.colors[500]}50`,
            boxShadow: `0 -2px 2px ${theme.colors[300]}20`,
            color: theme.colors[500]

        }}>
            <button
            onClick={() => setOpen(true)}
            className="font-beiruti text-lg font-semibold 
            tracking-wide leading-none
            flex items-center justify-center gap-2 
            "
            >
                <CalendarMonthIcon sx={{fontSize: 15}} className="me-2"/> 
                <div>
                    {filtrarFechaFormat(active, selectedDate)}  
                </div>
                <ArrowDropDownIcon sx={{fontSize: 25}}/>
            </button>

            <DatePicker
                label="Fecha"
                value={selectedDate}
                open={open}
                onChange={cambioFecha}
                onClose={() => setOpen(false)}
                slotProps={{
                    textField: {
                        sx: {
                            position: "relative",
                            opacity: 0,
                            width: 1,
                            height: 0,
                            pointerEvents: "none"
                        }
                    }
                }}
            />
        </div>
    );
};