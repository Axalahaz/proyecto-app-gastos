import { BalanceHeader } from "@/modules/balance/components/BalanceHeader";
import { BalanceNavigation } from "@/modules/balance/components/BalanceNavigation";

import { GastosHeader } from "@/modules/gastos/components/GastosHeader";
import { GastosNavigation } from "@/modules/gastos/components/GastosNavigation";
import { ModuleFilter } from "@/shared/components/ModuleFilter/ModuleFilter"

import * as themes from "@/shared/theme";
import type { LayoutConfig } from "@/routes/routesTypes";
import { CalculadoraGastos } from "@/modules/gastos/components/CalculadoraGastos";

/*
* Configuracion centralizada de rutas con sus componentes
*/

export const layoutConfig: Record<string, LayoutConfig> = {

    balance: {
        header: BalanceHeader,
        sidebarPrincipal: BalanceNavigation,
        sidebarPanel: {
            filter: ModuleFilter, 
            calculadora: CalculadoraGastos,
        },
        theme: themes.balanceTheme,
    },

    gastos: {
        header: GastosHeader,
        sidebarPrincipal: GastosNavigation,
        sidebarPanel: {
            filter: ModuleFilter, 
            calculadora: CalculadoraGastos,
        },
        theme: themes.gastosTheme,
    },

    // a modificar
    future: {
        header: BalanceHeader,
        sidebarPrincipal: BalanceNavigation,
        sidebarPanel: {
            filter: ModuleFilter, 
            calculadora: CalculadoraGastos,
        },
        theme: themes.balanceTheme,
    },
};