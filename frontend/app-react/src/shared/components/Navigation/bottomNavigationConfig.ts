import type { ElementType } from "react";

import {IconConfig} from "@/shared/icons/IconConfig";
import {IconIngresos} from "@/shared/icons/IconIngresos";
import {IconAhorros} from "@/shared/icons/IconAhorros";
import {IconEstadisticas} from "@/shared/icons/IconEstadisticas";
import {IconBalance} from "@/shared/icons/IconBalance";
import {IconGastos} from "@/shared/icons/IconGastos";
import {IconOk} from "@/shared/icons/IconOk";
import {IconVolver} from "@/shared/icons/IconVolver";


export type BottomSidebarItemm = {
    icon: ElementType | undefined;
    label: string;
    module: string;
};

export const bottomSidebarConfig = {
    volver: {
        icon: IconVolver,
        label: "Volver",
        module: "/future",
    },
    ok: {
        icon: IconOk,
        label: "",
        module: "/future",
    },
    config: {
        icon: IconConfig,
        label: "Config",
        module: "/future",
    },
    gastos: {
        icon: IconGastos,
        label: "Gastos",
        module: "/gastos",
    },
    ahorros: {
        icon: IconAhorros,
        label: "Ahorros",
        module: "/future",
    },
    ingresos: {
        icon: IconIngresos,
        label: "Ingresos",
        module: "/future",
    },
    estadisticas: {
        icon: IconEstadisticas,
        label: "Análisis",
        module: "/future",
    },
    balance: {
        icon: IconBalance,
        label: "Balance",
        module: "/balance",
    },
} as const satisfies Record<string, {
    icon: ElementType | undefined;
    label: string;
    module: string;
}>;