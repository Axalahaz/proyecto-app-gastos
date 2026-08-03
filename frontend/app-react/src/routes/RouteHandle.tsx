import type { LayoutConfig } from "./types/appLayoutTypes";
import type { PanelLayoutConfig } from "./types/modulesLayoutTypes";

export interface RouteHandle {
    appLayout?: LayoutConfig;
    moduleLayout?: PanelLayoutConfig;
}