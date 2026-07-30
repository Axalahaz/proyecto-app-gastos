import type { ThemeProps } from "@/shared/theme/themes";

export const SIDEBAR = {
    FILTER: "filter",
    CALCULADORA: "calculadora",
} as const;

export type SidebarMode =
    typeof SIDEBAR[keyof typeof SIDEBAR];

export interface SidebarPanelConfig {
    filter: React.ComponentType<ThemeProps>;
    calculadora: React.ComponentType<ThemeProps>;
}