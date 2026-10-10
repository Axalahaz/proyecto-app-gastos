import { FloatingAdd } from "@/shared/components/FloatingAdd/FloatingAdd";
import type { ThemeProps } from "@/shared/theme/themes";

interface Props extends ThemeProps {
    active: boolean;
    onClick: () => void;
}

export const FloatingAddGastos = ({
    active,
    onClick,
    theme,
}: Props) => {
    return (
        <FloatingAdd
            active={active}
            openCalculadora={onClick}
            labelBoton = "Agregar gasto"
            tituloBotom = "NUEVO GASTO"
            theme={theme}
        />
    );
}