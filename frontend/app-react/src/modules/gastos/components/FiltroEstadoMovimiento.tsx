import * as React from 'react';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { ESTADO_MOVIMIENTO, type estadoMovimiento } from '@/shared/types/typesApp';
import type { ThemeProps } from '@/shared/theme/themes';

interface Props extends ThemeProps {
    filtroEstado: estadoMovimiento | "TODOS"; 
    setFiltroEstado: (value: estadoMovimiento | "TODOS") => void;
}

export const RadioButtonsGroup = ({
    filtroEstado,
    setFiltroEstado,
    theme,
}: Props) => {
    const id = React.useId();

    // ----------------------------
    return (
        <FormControl 
            sx={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                gap: 3,
            }}
        >
            <FormLabel
                id={`${id}-label`}
                style={{
                    fontFamily:"var(--font-beiruti)",
                    fontSize: "1rem",
                    color: theme.colors[900], 
                }}
            >
                Estado Movimiento:
            </FormLabel>
            <RadioGroup
                row 
                aria-labelledby={`${id}-label`}
                defaultValue={filtroEstado}
                name="row-radio-buttons-group"
                onChange={(event) => {
                    const estado = event.target.value as (estadoMovimiento | "TODOS");
                    setFiltroEstado(estado);
                }}
            >
                
                <FormControlLabel 
                    value={ESTADO_MOVIMIENTO.ACTIVO} 
                    control={
                        <Radio 
                            size="small" 
                            sx={{
                                "&.Mui-checked": {
                                    color: theme.colors[700],
                                },
                            }}
                        />
                    }
                    label={ESTADO_MOVIMIENTO.ACTIVO}  
                    sx={{
                        "& .MuiFormControlLabel-label": {
                            fontFamily: "var(--font-beiruti)",
                            fontSize: "0.8rem",
                        },
                    }}
                />
                <FormControlLabel 
                    value={ESTADO_MOVIMIENTO.ANULADO} 
                    control={
                        <Radio
                            size="small" 
                            sx={{
                                "&.Mui-checked": {
                                    color: theme.colors[700],
                                },
                            }}
                        />
                    } 
                    label={ESTADO_MOVIMIENTO.ANULADO}  
                    sx={{
                        "& .MuiFormControlLabel-label": {
                            fontFamily: "var(--font-beiruti)",
                            fontSize: "0.8rem",
                        },
                    }}
                />
                <FormControlLabel 
                    value="TODOS" 
                    control={
                        <Radio
                            size="small" 
                            sx={{
                                "&.Mui-checked": {
                                    color: theme.colors[700],
                                },
                            }}
                        />
                    }
                    label="TODOS" 
                    sx={{
                        "& .MuiFormControlLabel-label": {
                            fontFamily: "var(--font-beiruti)",
                            fontSize: "0.8rem",
                        },
                    }}
                />
            </RadioGroup>
        </FormControl>
    );
}
