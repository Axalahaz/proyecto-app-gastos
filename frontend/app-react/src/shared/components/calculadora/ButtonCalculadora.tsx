import type { ThemeProps } from "@/shared/theme/themes";
import type { Tecla } from "@/shared/types/typesCalculadora";
import type { TeclaEspecial } from "@/shared/types/typesCalculadora";
import { TECLAS_ESPECIALES } from "@/shared/types/typesCalculadora";


interface ButtonCalculatorProps extends ThemeProps {
    value: string;
    onClick: (value: Tecla) => void;
    onDelete?: () => void;
    onClear?: () => void;
};

export const ButtonCalculadora = ({
    value,
    onClick,
    onDelete,
    onClear,
    theme,
}: ButtonCalculatorProps) => {

    const botonesEspeciales: TeclaEspecial[] = [
        TECLAS_ESPECIALES.IGUAL, 
        TECLAS_ESPECIALES.LIMPIAR, 
        TECLAS_ESPECIALES.BORRAR
    ];
    const esEspecial = (value: string): value is TeclaEspecial =>
            botonesEspeciales.includes(value as TeclaEspecial);
    
    return (
        <button
            onClick={() => {
                if (value === TECLAS_ESPECIALES.BORRAR) {
                    onDelete?.();
                    return;
                }
                if (value === TECLAS_ESPECIALES.LIMPIAR) {
                    onClear?.();
                    return;
                }
                onClick(value as Tecla);
            }}
            className={`rounded-full text-lg border font-beiruti font-semibold 
                active:scale-95 transition-transform`}
            style={
                esEspecial(value) 
                    ? {
                        background: theme.colors[100], 
                        color: theme.colors[700], 
                        borderColor: theme.colors[500], 
                        
                    } 
                    : {
                        background: theme.colors[700], 
                        color: theme.colors[100], 
                        borderColor: theme.colors[300], 
                    }
            }
        >
            {value}
        </button>
    );
};