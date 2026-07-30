import { useGastos } from "@/modules/gastos/context/useGastos";
import { SIDEBAR } from "../types/typeSidebar";
import type { ThemeProps } from "../theme/themes";
import type { SidebarPanelConfig } from "@/shared/types/typeSidebar";

interface sidebarPanel extends ThemeProps {
    moduleConfig:SidebarPanelConfig;
}

export const SidebarPanel = ({ 
    moduleConfig, 
    theme 
}: sidebarPanel) => {

    const { sidebarMode } = useGastos();

    const Component =
        sidebarMode === SIDEBAR.CALCULADORA
            ? moduleConfig.calculadora
            : moduleConfig.filter;

    return Component ? <Component theme={theme} /> : null;
};