import { useState } from "react";
import type { ThemeProps } from "@/shared/theme/themes";

import EditCalendarIcon from '@mui/icons-material/EditCalendar';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';

import type { Dayjs } from "dayjs";
import dayjs from "dayjs";
import { formatDateInput } from "@/shared/utils/compareFechasUtils";
import { defaultTheme } from "@/shared/theme";
import { CustomActionBar } from "./CustomActionBar";
import { DateCalendar } from "@mui/x-date-pickers";
import Popover from "@mui/material/Popover";

interface Props extends ThemeProps {
    fecha: Dayjs;
}

export const ButtonCalendario = ({
    fecha,
    theme,
}: Props) => {
    dayjs.locale("es");

    // --------------
    // Fecha del gasto

    // Fecha confirmada
    const [fechaOriginal, setFechaOriginal] = useState<Dayjs>(fecha)
    // Fecha que se está modificando dentro del calendario
    const [fechaCambiada, setFechaCambiada] = useState<Dayjs>(fecha)

    // ---------------------------- 
    // POPOVER 
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null); 
    const open = Boolean(anchorEl);
    
    // ----------------------------
    // ABRIR / CERRAR
    const handleOpen = (
        event: React.MouseEvent<HTMLButtonElement>
    ) => {
        // Cada vez que abrimos el calendario,
        // arrancamos desde la fecha confirmada.
        setFechaCambiada(fechaOriginal);

        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        // Al cerrar haciendo click afuera,
        // descartamos cualquier cambio.
        setFechaCambiada(fechaOriginal);

        setAnchorEl(null);
    };

    // ----------------------------
    // CAMBIO DE FECHA
    const cambioFecha = (newDate: Dayjs | null) => {
        if (!newDate) return;

        setFechaCambiada(newDate);
    };

    // ----------------------------
    // CANCELAR
    const handleCancel = () => {
        setFechaCambiada(fechaOriginal);
        setAnchorEl(null);
    };

    // ----------------------------
    // ACEPTAR
    const handleAccept = () => {
        setFechaOriginal(fechaCambiada);
        setAnchorEl(null);
    };

    // ----------------------------
    // ESTILOS CALENDARIO
    // ----------------------------

    const calendarSx = {
        width: 320,

        // Flechas
        "& .MuiPickersArrowSwitcher-button": {
            color: theme.colors[900],
        },

        "& .MuiPickersArrowSwitcher-button:hover": {
            backgroundColor: theme.colors[300],
        },

        // Nombre de los días
        "& .MuiDayCalendar-weekDayLabel": {
            color: defaultTheme.colors[900],
            fontWeight: 600,
        },

        // Mes / año
        "& .MuiPickersCalendarHeader-label": {
            color: theme.colors[900],
            fontWeight: 700,
        },

        // Días
        "& .MuiPickersDay-root": {
            "&:not(.Mui-disabled)": {
                color: theme.colors[900],
            },

            "&.Mui-selected": {
                backgroundColor: theme.colors[700],
                color: theme.colors[100],
            },

            "&.Mui-selected:hover": {
                backgroundColor: theme.colors[300],
                color: theme.colors[900],
            },

            "&:hover:not(.Mui-disabled)": {
                backgroundColor: theme.colors[300],
            },

            "&.MuiPickersDay-today": {
                border: `3px solid ${theme.colors[300]}`,
            },
        },

        // Años
        "& .MuiYearCalendar-button": {
            color: theme.colors[900],
        },

        "& .MuiYearCalendar-button.Mui-selected": {
            backgroundColor: theme.colors[700],
            color: theme.colors[100],
        },

        "& .MuiYearCalendar-button.Mui-selected:hover": {
            backgroundColor: theme.colors[300],
            color: theme.colors[900],
        },

        "& .MuiYearCalendar-button:hover": {
            backgroundColor: theme.colors[300],
        },
    };

    // ----------------------
    return (
        <div 
            className="flex flex-col " 
            style={{  color: defaultTheme.colors[700],}}
        >
            {/* Botón */}
            <div 
                className={`z-50 
                    ${open && `-m-2 p-2 transition-all duration-300 rounded-[5px] `}
                `}
                style={{
                    backgroundColor: open
                        ? `${theme.colors[100]}`
                        : "transparent",
                    boxShadow: open
                        ? `0 0 10px 2px ${theme.colors[300]}`
                        : "none",
                }}
            >
                <button
                    className=" 
                    font-beiruti text-base
                    tracking-wide leading-none 
                    flex items-end gap-2"
                    onClick={handleOpen}
                >
                    <EditCalendarIcon  
                        sx={{ 
                            fontSize: 20, 
                            color: theme.colors[900],
                        }}
                    /> 
                    <div>
                        {formatDateInput(fechaOriginal)}  
                        <ArrowDropDownIcon sx={{fontSize: 15}}/>
                    </div>
                </button>
            </div>
            {/* OVERLAY */}
            {open && (
                <div
                    className="fixed inset-0 z-40"
                    style={{
                        backgroundColor: "rgba(0, 0, 0, 0.90)",
                    }}
                    onClick={handleClose}
                />
            )}
            {/* POPOVER */}
            <Popover
                open={open}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "center",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "center",
                }}
                slotProps={{
                    paper: {
                        sx: {
                            zIndex: 50,
                            width: 320,
                            overflow: "hidden",
                            borderRadius: "8px",
                            boxShadow:
                                `0 0 10px 2px ${theme.colors[300]}`,
                        },
                    },
                }}
            >
                <DateCalendar
                    value={fechaCambiada}
                    onChange={cambioFecha}
                    yearsOrder={"desc"}
                    minDate={dayjs("2000-12-01")}
                    maxDate={dayjs()}
                    disableFuture
                    sx={calendarSx}
                />
                <CustomActionBar
                    cambioFecha={cambioFecha}
                    onCancel={handleCancel}
                    onAccept={handleAccept}
                    theme={theme}
                />
            </Popover>
        </div>
    );
};