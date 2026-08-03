import { BalanceHeader } from "@/modules/balance/components/BalanceHeader";
import { BalanceNavigation } from "@/modules/balance/components/BalanceNavigation";

import { GastosHeader } from "@/modules/gastos/components/GastosHeader";
import { GastosNavigation } from "@/modules/gastos/components/GastosNavigation";

import * as themes from "@/shared/theme";
import type { LayoutConfig } from "@/routes/types/appLayoutTypes";

/*
* Configuracion centralizada de rutas con sus componentes
*/

export const appLayoutConfig: Record<string, LayoutConfig> = {

    balance: {
        header: BalanceHeader,
        sidebarPrincipal: BalanceNavigation,
        theme: themes.balanceTheme,
    },

    gastos: {
        header: GastosHeader,
        sidebarPrincipal: GastosNavigation,
        theme: themes.gastosTheme,
    },

    // a modificar
    future: {
        header: BalanceHeader,
        sidebarPrincipal: BalanceNavigation,
        theme: themes.balanceTheme,
    },
};