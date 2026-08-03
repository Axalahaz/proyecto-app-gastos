import type { ThemeProps } from "@/shared/theme/themes";

export interface LayoutConfig extends ThemeProps {
    header: React.ComponentType<ThemeProps>;
    sidebarPrincipal: React.ComponentType<ThemeProps>;
};
