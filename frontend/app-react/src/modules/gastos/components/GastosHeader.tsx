import { AppHeader } from "@/shared/components/AppHeader";

import type { ThemeProps } from "@/shared/theme/themes";

export const GastosHeader = ({theme}: ThemeProps) => {
    return (
            <AppHeader
                title={"• CALCULÁ FACILMENTE TUS GASTOS •"}
                theme={theme}
            />
    );
};