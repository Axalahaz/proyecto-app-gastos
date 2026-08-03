export const SIDEBAR = {
    FILTER: "filter",
    CALCULADORA: "calculadora",
} as const;

export type SidebarMode =
    typeof SIDEBAR[keyof typeof SIDEBAR];
