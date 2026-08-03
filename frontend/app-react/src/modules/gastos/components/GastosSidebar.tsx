import { useGastos } from "@/modules/gastos/context/useGastos";

import { SIDEBAR } from "@/shared/types/typeSidebar";

import type { ThemeProps } from "@/shared/theme/themes";
import { CalculadoraGastos } from "@/modules/gastos/components/CalculadoraGastos";
import { ModuleFilter } from "@/shared/components/ModuleFilter/ModuleFilter";

export const GastosSidebar = ({ theme }: ThemeProps) => {

    const { sidebarMode } = useGastos();

    return sidebarMode === SIDEBAR.CALCULADORA
        ? <CalculadoraGastos theme={theme} />
        : <ModuleFilter theme={theme} />;
};