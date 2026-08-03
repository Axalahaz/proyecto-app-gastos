import { ModuleFilter } from "@/shared/components/ModuleFilter/ModuleFilter"

import * as themes from "@/shared/theme";
import type { PanelLayoutConfig } from "@/routes/types/modulesLayoutTypes";
import { GastosSidebar } from "@/modules/gastos/components/GastosSidebar";

/*
* Configuracion centralizada de rutas con sus componentes
*/

export const moduleLayoutConfig: Record<string, PanelLayoutConfig> = {

    balance: {
        sidebarPanel:  ModuleFilter,
        theme: themes.balanceTheme,
    },

    gastos: {
        sidebarPanel: GastosSidebar,
        theme: themes.gastosTheme,
    },

    // a modificar
    future: {
        sidebarPanel: GastosSidebar,
        theme: themes.balanceTheme,
    },
};