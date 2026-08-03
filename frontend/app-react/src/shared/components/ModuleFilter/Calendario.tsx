import { FILTERS } from "@/context/typesFilter";
import type { FilterType } from "@/context/typesFilter";
import { useFilter } from "@/hooks/useFilter";
import type { Dayjs } from "dayjs";
import { DateCalendar, YearCalendar } from "@mui/x-date-pickers";
import dayjs from "dayjs";
import { RangeCalendario } from "./RangeCalendario";

import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme";

interface Props extends ThemeProps {
    filter: FilterType;
}

export const Calendario = ({
    filter,
    theme,
}: Props) => {

    const {
        selectedDate,
        setSelectedDate,
    } = useFilter();

    const cambioFecha = (newDate: Dayjs | null) => {
        if (!newDate) return;
        setSelectedDate(newDate);
    };

    // -----------------
    // ESTILOS GENERICOS
    const commonCalendarProps = {
        yearsOrder: "desc" as const,
        minDate: dayjs("2000-12-01"),
        maxDate: dayjs(),
    };
    const getClassCalendar = "rounded-[20px] mt-3 p-2"
    const getSXCalendarComponent = {
        background: "white",
        height: 350,
    };
    const getSXCalendarStyles = {
        "& .MuiPickerDay-root": {
            color: theme.colors[900],

            "&.Mui-selected": {
                backgroundColor: theme.colors[700],
                color: theme.colors[100],
            },

            "&:hover": {
                backgroundColor: `${theme.colors[500]}40`,
            },
        },

        "& .MuiPickerDay-today": {
            border: `3px solid ${theme.colors[300]}`,
        },

        "& .MuiPickerDay-root.Mui-disabled": {
            color: defaultTheme.colors[900],
        },

        "& .MuiMonthCalendar-button": {
            "&.Mui-selected": {
                backgroundColor: theme.colors[700],
                border: `1px solid ${theme.colors[300]}`,
                color: theme.colors[100],
            },
        },

        "& .MuiYearCalendar-button": {
            "&.Mui-selected": {
                backgroundColor: theme.colors[700],
                border: `1px solid ${theme.colors[300]}`,
                color: theme.colors[100],
            },
        },

    };

    // ----------------
    // SELECCION CALENDARIO SEGUN FILTRO
    return (
        <div
            className={`flex flex-col items-center gap-5 rounded-[20px]`} 
            style={{
                transform: "scale(0.80)",
                boxShadow: `1px 1px 5px ${theme.colors[300]}`,
                ...getSXCalendarComponent,
            }}            
        >
            {filter === FILTERS.DIARIO.value
                && <DateCalendar
                        {...commonCalendarProps}
                        className={getClassCalendar}
                        value={selectedDate}
                        onChange={cambioFecha}
                        disableFuture
                        sx={{
                            ...getSXCalendarComponent,
                            ...getSXCalendarStyles,
                        }}
                    />
            }
            {filter === FILTERS.MENSUAL.value
                && <DateCalendar
                        {...commonCalendarProps}
                        className={getClassCalendar}
                        defaultValue={selectedDate}
                        views={["year", "month"]}
                        openTo="year"
                        value={selectedDate}
                        onChange={cambioFecha}
                        shouldDisableMonth={(month) =>
                            month.isAfter(dayjs(), "month")
                        }
                        sx={{
                            ...getSXCalendarComponent,
                            ...getSXCalendarStyles,
                        }}
                    />
            }
            {filter === FILTERS.ANUAL.value
                && <YearCalendar
                        {...commonCalendarProps}
                        className={getClassCalendar}
                        value={selectedDate}
                        onChange={cambioFecha}
                        sx={{
                            ...getSXCalendarComponent,
                            ...getSXCalendarStyles,
                        }}
                    />
            }
            {filter === FILTERS.PERIODO.value
                && <RangeCalendario 
                    common={commonCalendarProps} 
                    className={getClassCalendar} 
                    sx={{...getSXCalendarComponent,}}
                    theme={theme} 
                    buttonDays={selectedDate}
                />
            }
            <button
                onClick={() => setSelectedDate(dayjs())}
                className="w-[20dvh] mb-4 border rounded-[20px] text-sm font-semibold active:scale-120"
                style={{ 
                    borderColor: theme.colors[500],
                    color: theme.colors[700] 
                }}
            >
                Hoy
            </button>
        </div>
    );
}