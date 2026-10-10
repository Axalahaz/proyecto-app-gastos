import React, { useEffect, useState } from "react";

import FormControlLabel from "@mui/material/FormControlLabel";
import type { ThemeProps } from "@/shared/theme/themes";

import { SelectMes } from "./SelectMes";
import NumberSpinner from "./NumberSpinner";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import RadioGroup from "@mui/material/RadioGroup";
import Radio from "@mui/material/Radio";
import { FRECUENCIA, type frecuencia } from "@/modules/gastos/types/typeGastos";
import { defaultTheme } from "@/shared/theme";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import type { FormularioGasto } from "@/modules/gastos/dto/gasto/FormularioGasto";

interface Props extends ThemeProps {
    formRecurrencia: FormularioGasto;
    setFormRecurrencia: React.Dispatch<
        React.SetStateAction<FormularioGasto>
    >;
    open: boolean;
    onClose: () => void;
}

export const ModalRecurrencia = ({
    formRecurrencia,
    setFormRecurrencia,
    theme,
    open,
    onClose
}: Props) => {

    const id = React.useId();

    // --------------------------------
    // FORMULARIO TEMPORAL
    const [formNuevo, setFormNuevo] = useState<FormularioGasto>({
        ...formRecurrencia,
    });

    // --------------------------------
    // AL ABRIR EL MODAL
    useEffect(() => {
        if (!open) return;

        setFormNuevo({
            ...formRecurrencia,
            diaVencimiento: formRecurrencia.diaVencimiento ?? 1,
            mesVencimiento: formRecurrencia.mesVencimiento ?? undefined,
            frecuencia: formRecurrencia.frecuencia ?? FRECUENCIA.MENSUAL,
        });
    }, [open, formRecurrencia]);

    // --------------------------------
    // CANCELAR
    const handleCancelar = () => {
        // No modificamos formRecurrencia
        onClose();
    };

    // --------------------------------
    // OK
    const handleAceptar = () => {
        setFormRecurrencia(formNuevo);
        onClose();
    };

    return (
        <Dialog 
            open={open}
            onClose={handleCancelar}
            
        >
            <DialogTitle 
                className="text-center" 
                style={{
                    fontFamily:"var(--font-beiruti)",
                    fontSize: "1.1rem",
                    color: theme.colors[900], 
                }}
            >
                REGISTRAR RECURRENCIA
            </DialogTitle>
            <DialogContent className="flex flex-col gap-3">
                <FormControl 
                    sx={{
                        "& .MuiButton-root": {
                            borderColor: "divider",
                            minWidth: 0,
                            bgcolor: "action.hover",
                            "&:not(.Mui-disabled)": {
                                color: "text.primary",
                            },
                        },
                    }}
                >
                    <FormLabel
                        id={`${id}-label`}
                        sx={{
                            color: defaultTheme.colors[900],
                            fontSize: "0.9rem",
                            "&.Mui-focused": {
                                color: defaultTheme.colors[900],
                            },
                        }}
                    >
                        Frecuencia
                    </FormLabel>
                    <RadioGroup
                        value={formNuevo.frecuencia}
                        onChange={(event) => {
                            const frecuencia = event.target.value as frecuencia;
                            setFormNuevo((prev) => ({
                                ...prev,
                                frecuencia,
                                mesVencimiento:
                                    frecuencia === FRECUENCIA.MENSUAL
                                        ? undefined
                                        : prev.mesVencimiento ?? 1,
                            }));
                        }}
                        aria-labelledby={`${id}-label`}
                    >
                        <FormControlLabel
                            value={FRECUENCIA.MENSUAL}
                            control={
                                <Radio
                                    sx={{
                                        "&.Mui-checked": {
                                            color: theme.colors[700],
                                        },
                                    }}
                                />
                            }
                            label={FRECUENCIA.MENSUAL}
                            sx={{
                                color:
                                    formNuevo.frecuencia === FRECUENCIA.MENSUAL
                                        ? theme.colors[700]
                                        : "inherit",
                            }}
                        />
                        <FormControlLabel
                            value={FRECUENCIA.ANUAL}
                            control={
                                <Radio
                                    sx={{
                                        "&.Mui-checked": {
                                            color: theme.colors[700],
                                        },
                                    }}
                                />
                            }
                            label={FRECUENCIA.ANUAL}
                            sx={{
                                color:
                                    formNuevo.frecuencia === FRECUENCIA.ANUAL
                                        ? theme.colors[700]
                                        : "inherit",
                            }}
                        />
                    </RadioGroup>
                </FormControl>
        {/** Contenedor Dia */}
                    <NumberSpinner
                        required
                        label="Día del mes"
                        min={1}
                        max={31}
                        size="small"
                        value={formNuevo.diaVencimiento}
                        onValueChange={(value) => {
                            if (value === null) return;

                            setFormNuevo((prev) => ({
                                ...prev,
                                diaVencimiento: value,
                            }));
                        }}
                        theme={theme}
                    />
        {/** Contenedor Mes */}
                    <SelectMes 
                        disabled={formNuevo.frecuencia === FRECUENCIA.MENSUAL}
                        value={formNuevo.mesVencimiento}
                        onChange={(mes) => {
                            setFormNuevo((prev) => ({
                                ...prev,
                                mesVencimiento: mes,
                            }));
                        }}
                        theme={theme}
                    />
            </DialogContent>
        {/** Contenedor Botones */}
            <DialogActions>
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