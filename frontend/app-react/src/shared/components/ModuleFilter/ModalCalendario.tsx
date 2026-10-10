import { FILTERS } from "@/context/typesFilter";
import type { FilterType } from "@/context/typesFilter";
import { useFilter } from "@/hooks/useFilter";
import type { Dayjs } from "dayjs";
import { DateCalendar, YearCalendar } from "@mui/x-date-pickers";
import dayjs from "dayjs";
import { RangeCalendario } from "./RangeCalendario";

import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme";
import { useEffect, useState } from "react";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";

interface Props extends ThemeProps {
    filter: FilterType;
    open: boolean;
    onClose: () => void;
}

export const ModalCalendario = ({
    filter,
    open,
    onClose,
    theme,
}: Props) => {

    const {
        selectedDate,
        setSelectedDate,
        from,
        to,
        setFrom,
        setTo,
    } = useFilter();

    // --------------------------------
    // FORMULARIO TEMPORAL AL ABRIR MODAL
    const [dateNuevo, setDateNuevo] = useState<Dayjs>(dayjs());
    const [dateFromNuevo, setDateFromNuevo] = useState<Dayjs>(dayjs());
    const [dateToNuevo, setDateToNuevo] = useState<Dayjs>(dayjs());

    useEffect(() => {
        if (!open) return;
        if (filter === FILTERS.PERIODO.value) {
            setDateFromNuevo(from);
            setDateToNuevo(to);
            return
        }
        setDateNuevo(selectedDate);
    }, [open, filter, selectedDate,from,to]);


    // --------------------------------
    // CANCELAR
    const handleCancelar = () => {
        // No modificamos formRecurrencia
        onClose();
    };

    // --------------------------------
    // OK
    const handleAceptar = () => {
        if (filter === FILTERS.PERIODO.value) {
            setFrom(dateFromNuevo);
            setTo(dateToNuevo);
            onClose();
            return
        }
        setSelectedDate(dateNuevo);
        onClose();
    };
    
    const cambioFechaSimple = (newDate: Dayjs | null) => {
        if (!newDate) return;
        setDateNuevo(newDate);
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
    return (
        <Dialog 
            open={open}
            onClose={handleCancelar}
            className="flex items-center justify-center"
            sx={{
                borderColor: theme.colors[900],
                transform: "scale(0.75)",
            }}
        >
            <DialogTitle 
                className="text-center" 
                style={{
                    paddingBottom: 0,
                    fontFamily:"var(--font-beiruti)",
                    fontSize: "1.5rem",
                    color: theme.colors[900], 
                }}
            >
                CALENDARIO
            </DialogTitle>
            <DialogContent
                className="flex flex-col gap-3"
            >
                <div className="" >
                    {filter === FILTERS.DIARIO.value
                        && <DateCalendar
                                {...commonCalendarProps}
                                className={getClassCalendar}
                                value={dateNuevo}
                                onChange={cambioFechaSimple}
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
                                defaultValue={dateNuevo}
                                views={["year", "month"]}
                                openTo="year"
                                value={dateNuevo}
                                onChange={cambioFechaSimple}
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
                                value={dateNuevo}
                                onChange={cambioFechaSimple}
                                sx={{
                                    ...getSXCalendarComponent,
                                    ...getSXCalendarStyles,
                                }}
                            />
                    }
                    {filter === FILTERS.PERIODO.value
                        && <RangeCalendario 
                            from={dateFromNuevo}
                            to={dateToNuevo}
                            setFrom={setDateFromNuevo}
                            setTo={setDateToNuevo}
                            common={commonCalendarProps} 
                            className={getClassCalendar} 
                            sx={{...getSXCalendarComponent,}}
                            buttonDays={dateNuevo}
                            theme={theme} 
                        />
                    }
                </div>
            </DialogContent>
        {/** Contenedor Botones */}
            <DialogActions>
                <Button
                    onClick={() => setDateNuevo(dayjs())}
                    sx={{
                        backgroundColor: theme.colors[300],
                        color: theme.colors[900],

                        fontFamily: "var(--font-beiruti)",
                        fontSize: "1rem", 

                        "&:hover": { 
                            backgroundColor: theme.colors[900],
                            color: theme.colors[300],
                        },
                    }}
                >
                    Hoy
                </Button>
                <Button
                    onClick={handleCancelar}
                    sx={{
                        backgroundColor: theme.colors[900],
                        color: theme.colors[100],

                        fontFamily: "var(--font-beiruti)",
                        fontSize: "1rem", 

                        "&:hover": { 
                            backgroundColor: theme.colors[300],
                            color: theme.colors[900],
                        },
                    }}
                >
                    Cancelar
                </Button>
                <Button
                    onClick={handleAceptar}
                    sx={{
                        backgroundColor: theme.colors[900],
                        color: theme.colors[100],

                        fontFamily: "var(--font-beiruti)",
                        fontSize: "1rem", 

                        "&:hover": { 
                            backgroundColor: theme.colors[300],
                            color: theme.colors[900],
                        },
                    }}
                >
                    Ok
                </Button>
            </DialogActions>
        </Dialog>
    );
}