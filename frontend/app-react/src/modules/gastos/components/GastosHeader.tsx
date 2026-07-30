import { AppHeader } from "@/shared/components/AppHeader";

import type { ThemeProps } from "@/shared/theme/themes";

import { IconGastos } from "@/shared/icons/IconGastos";

export const GastosHeader = ({theme}: ThemeProps) => {
    return (
            <AppHeader
                title="GASTOS"
                icon={IconGastos}
                classIcon="w-6 h-6 text-white"
                theme={theme}
            />
    );
};