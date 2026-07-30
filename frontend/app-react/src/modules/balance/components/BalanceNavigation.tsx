import { AppNavigation } from "@/shared/components/Navigation/AppNavigation";

import type { ThemeProps } from "@/shared/theme/themes";

export const BalanceNavigation = ({theme}: ThemeProps) => {
    return (
        <AppNavigation
            title= "¡Ayudandote en tu economia!"
            theme={theme}
        />
    );
};