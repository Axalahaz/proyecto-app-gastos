import { AppNavigation } from "@/shared/components/Navigation/AppNavigation";

import type { ThemeProps } from "@/shared/theme/themes";

export const GastosNavigation  = ({theme}: ThemeProps) => {
    return (
        <AppNavigation
            theme={theme}
        />
    );
};