import type { FormularioGasto } from "@/modules/gastos/dto/gasto/FormularioGasto";
import type { ThemeProps } from "@/shared/theme/themes";
import TextField from "@mui/material/TextField";
import type { ChangeEvent } from "react";

interface Props extends ThemeProps {
    campo: string;
    setForm: React.Dispatch<React.SetStateAction<FormularioGasto>>;
}

export const Input = ({
    campo,
    setForm,
    theme,
}: Props) => {

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        const nuevaDescripcion = event.target.value;
        setForm(prev => ({
            ...prev,
            descripcion: nuevaDescripcion,
        }));
    };

    return (
        <TextField
            value={campo}
            placeholder="Escribir..."
            onChange={handleChange}
            size="small"
            fullWidth
            sx={{
                "& .MuiInputLabel-root": {
                    fontFamily: "var(--font-beiruti)",
                    fontSize: "1rem",
                    color: theme.colors[900],
                },

                "& .MuiInputLabel-root.Mui-focused": {
                    color: theme.colors[700],
                },

                "& .MuiOutlinedInput-root": {
                    fontFamily: "var(--font-beiruti)",
                    fontSize: "1rem",

                     // Sin bordes laterales/superior
                    "& fieldset": {
                        border: "none",
                        borderRadius: 0,
                    },
                },
            }}
        />
    );
}