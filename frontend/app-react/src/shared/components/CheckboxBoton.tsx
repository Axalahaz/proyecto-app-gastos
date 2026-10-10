import type { ThemeProps } from "@/shared/theme/themes";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormGroup from "@mui/material/FormGroup";

interface Props extends ThemeProps {
    texto: string;
    fontSize?: string;
    fontFamily?: string;

    esActivo: boolean;
    setEsActivo: React.Dispatch<React.SetStateAction<boolean>>;

    sizeCheckbox?: "small" | "medium";
    icon?: React.ReactNode;
    checkedIcon?: React.ReactNode;

    checkboxSx?: object;
}

export const CheckboxBoton = ({
    texto,
    fontSize = "1rem",
    fontFamily = "var(--font-sans)",
    esActivo,
    setEsActivo,
    sizeCheckbox = "small",
    icon,
    checkedIcon,
    checkboxSx,
    theme,
}: Props) => {

    return (
        <FormGroup>
            <FormControlLabel
                control={
                    <Checkbox
                        size={sizeCheckbox}
                        icon={icon}
                        checkedIcon={checkedIcon}
                        checked={esActivo}
                        onChange={(event) => {
                            setEsActivo(event.target.checked);
                        }}
                        sx={{
                            padding: 0,
                            color: theme.colors[900],

                            "&.Mui-checked": {
                                color: theme.colors[700],
                            },

                            ...checkboxSx,
                        }}
                    />
                }
                label={texto}
                sx={{
                    margin: 0,
                    gap: 1,
                    "& .MuiFormControlLabel-label": {
                        fontFamily,
                        fontSize,
                    },
                }}
            />
        </FormGroup>
    );
}