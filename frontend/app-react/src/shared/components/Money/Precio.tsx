import type { ThemeProps } from "@/shared/theme/themes";

interface PrecioProps extends ThemeProps { 
    valor: Number;
    enteroClassName: string;
    decimalClassName?: string;
}

export const Precio = ({ 
    valor, 
    enteroClassName = "",
    decimalClassName = "",
    theme,
}: PrecioProps) => {
    const [entero, decimales] = valor
        .toLocaleString("es-AR", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2,
        })
        .split(",");

    return (
        <p 
            className={`leading-none font-beiruti  ${enteroClassName}`}
            style={{color: theme.colors[700],}}
        >
            $ {entero}
            <sup className={`ps-1 ${decimalClassName}`}>
                {decimales}
            </sup>
        </p>
    );
}