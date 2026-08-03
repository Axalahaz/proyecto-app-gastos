import { AppHeader } from "@/shared/components/AppHeader";

import type { ThemeProps } from "@/shared/theme/themes";

export const BalanceHeader = ({theme}: ThemeProps) => {
    return (
        <AppHeader
            title={"• A primera vista un resumen de tus cuentas •"}
            theme={theme}
        />
    );
};