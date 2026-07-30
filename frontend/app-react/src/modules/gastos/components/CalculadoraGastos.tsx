import { Calculadora } from "@/shared/components/Calculadora";
import type { ThemeProps } from "@/shared/theme/themes";

export const CalculadoraGastos = ({theme}: ThemeProps) => {
    return (
        <Calculadora theme={theme} />
    );
}