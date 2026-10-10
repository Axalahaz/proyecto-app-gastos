import type { ThemeProps } from "@/shared/theme/themes";
import Button from "@mui/material/Button";
import type { Dayjs } from "dayjs";
import dayjs from "dayjs";

interface Props extends ThemeProps  {
    cambioFecha: (newDate: Dayjs | null) => void
    onCancel: () => void;
    onAccept: () => void;
}

export const CustomActionBar = ({
    cambioFecha,
    onCancel,
    onAccept,
    theme,
}: Props) => {

    return (
        <div className="flex justify-end gap-2 p-2">
            <Button
                onClick={() => {
                    cambioFecha(dayjs());
                }}
                sx={{
                    border: `1px solid ${theme.colors[700]}`,
                    color: theme.colors[900],
                    fontWeight: 600,
                    minWidth: 50,
                    "&:hover": {
                        backgroundColor: theme.colors[300],
                    },
                }}
            >
                Hoy
            </Button>
            <Button
                onClick={onCancel}
                sx={{
                    color: theme.colors[900],
                    fontWeight: 600,
                    minWidth: 50,
                    "&:hover": {
                        backgroundColor: theme.colors[300],
                    },
                }}
            >
                Cancelar
            </Button>
            <Button
                onClick={onAccept}
                sx={{
                    color: theme.colors[900],
                    fontWeight: 600,
                    minWidth: 50,
                    "&:hover": {
                        backgroundColor: theme.colors[300],
                    },
                }}
            >
                Aceptar
            </Button>
        </div>
    );
};