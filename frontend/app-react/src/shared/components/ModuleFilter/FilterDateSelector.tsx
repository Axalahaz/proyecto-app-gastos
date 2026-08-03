import { useFilter } from "@/hooks/useFilter";
import { Calendario } from "@/shared/components/ModuleFilter/Calendario";
import { FILTERS } from "@/context/typesFilter";

import { filtrarFechaFormat } from "@/shared/utils/filtrarFechaFormat";
import { formatDateInput } from "@/shared/utils/compareFechasUtils";

import type { FilterType } from "@/context/typesFilter";
import type { ThemeProps } from "@/shared/theme/themes";

import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

import "dayjs/locale/es";

interface Props extends ThemeProps {
    filter: FilterType;
}

export const FilterDateSelector = ({
    filter,
    theme
}: Props) => {
    // --------------
    // Obtengo fecha del filtro
    const { selectedDate, from, to } = useFilter();

    return (
        <div 
            className="flex flex-col items-center border rounded-[20px]
            font-beiruti text-lg font-semibold 
            tracking-wide leading-none pt-4"
            style={{
                background: theme.colors[100],
                borderColor: `${theme.colors[500]}50`,
                boxShadow: `0 -2px 2px ${theme.colors[300]}20`,
                color: theme.colors[700]
            }}
        >
            <CalendarMonthIcon sx={{fontSize: 25}}/> 
            <div className="py-3 ">
                { filter === FILTERS.PERIODO.value 
                    && (    
                        <div className="flex flex-col gap-1">
                            <p>
                                Desde: {formatDateInput(from)}
                            </p>
                            <p>
                                Hasta: {formatDateInput(to)} 
                            </p>
                        </div>
                    )
                    || <div className="">
                        {filtrarFechaFormat(filter, selectedDate)}
                    </div>
                }
            </div>
            <div className="-mt-7 overflow-hidden -me-4 -ms-4 -mb-4">
                <Calendario 
                    filter = {filter}
                    theme={theme}
                />
            </div>
        </div>
    );
};