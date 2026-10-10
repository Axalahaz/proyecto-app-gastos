import * as React from 'react';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

import { MES } from '@/modules/gastos/types/typeGastos'
import MenuItem from '@mui/material/MenuItem';
import { defaultTheme } from '@/shared/theme';
import type { ThemeProps } from '@/shared/theme/themes';
import FormLabel from '@mui/material/FormLabel';

interface Props extends ThemeProps {
    value?: number;
    onChange: (mes: number) => void;
    disabled?: boolean;
}

export const SelectMes = ({ 
    value, 
    onChange,
    disabled = false,
    theme,
}: Props) => {
    const id = React.useId();

    return (
        <FormControl
        required
        size="small"
        disabled={disabled}
        sx={{
                minWidth: 140,
                // Label
                "& .MuiInputLabel-root": {
                    color: defaultTheme.colors[900],
                    fontSize: "1.2rem",
                },

                // Label cuando está enfocado
                "& .MuiInputLabel-root.Mui-focused": {
                    color: theme.colors[900],
                },

                // Select
                "& .MuiOutlinedInput-root": {
                    fontFamily: "var(--font-beiruti)",

                    "& fieldset": {
                        borderColor: theme.colors[500],
                    },

                    "&:hover fieldset": {
                        borderColor: theme.colors[500],
                    },

                    "&.Mui-focused fieldset": {
                        borderColor: theme.colors[900],
                        borderWidth: 2,
                    },
                },

            }}
        >
            <FormLabel 
                id={`${id}-label`} 
                sx={{
                    color: defaultTheme.colors[900],
                    "&.Mui-focused": {
                        color: defaultTheme.colors[900],
                    },
                }}
            >
                Mes
            </FormLabel>
            <Select<number>
                value={value ?? 1}
                disabled={disabled}
                onChange={(event) => {
                    onChange(Number(event.target.value));
                }}
                sx={{
                    fontSize: "1.3rem",
                }}
            >
                {MES.map((mes) => (
                    <MenuItem 
                        key={mes.value} 
                        value={mes.value}
                        sx={{
                            fontFamily: "var(--font-beiruti)",
                            fontSize: "1.3rem",
                            "&.Mui-selected": {
                                backgroundColor: theme.colors[300],
                                color: theme.colors[900],
                            },
                            "&.Mui-selected:hover": {
                                backgroundColor: theme.colors[900],
                                color: defaultTheme.colors[100]
                            },
                            "&:hover": {
                                backgroundColor: theme.colors[300],
                            },
                        }}
                    >
                        {mes.label}
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    );
};