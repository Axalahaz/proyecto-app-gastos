import type { ThemeProps } from "@/shared/theme/themes";
import type { SidebarPanelConfig } from "@/shared/types/typeSidebar";

/*
* Configuracion centralizada de rutas con sus componentes
*/
export interface LayoutConfig extends ThemeProps {
    header: React.ComponentType<ThemeProps>;
    sidebarPrincipal: React.ComponentType<ThemeProps>;
    sidebarPanel: SidebarPanelConfig;
};

export interface RouteHandle {
    layout: LayoutConfig;
}
