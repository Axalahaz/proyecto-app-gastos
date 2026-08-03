import type { ThemeProps } from "@/shared/theme/themes";

export interface PanelLayoutConfig extends ThemeProps {
    sidebarPanel: React.ComponentType<ThemeProps>;
};
