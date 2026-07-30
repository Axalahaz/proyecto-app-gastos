import { Outlet, useMatches } from "react-router-dom";

import type { RouteHandle } from "@/routes/routesTypes";
import { defaultTheme } from "@/shared/theme";

import { SidebarPanel } from "@/shared/components/SidebarPanel";

export const AppLayout = () => {
    // --------------
    // Obtiene el objeto de la ruta y le establece un id
    const matches = useMatches();

    // --------------
    // ultima ruta
    const currentRoute = matches[matches.length - 1];

    // --------------
    // Establece si existen configuraciones para la ruta
    const config = (currentRoute.handle as RouteHandle | undefined)?.layout;

    if (!config) {
        return <Outlet />;
    }

    const {
        header: Header,
        sidebarPrincipal: SidebarPrincipal,
        sidebarPanel: SidebarPanelConfig,
        theme = defaultTheme,
    } = config ?? { theme: defaultTheme };

    return (
        <div className="flex flex-col h-screen">

            <header className="w-full shrink-0">
                <Header theme = {theme}/>
            </header>

            <div className="flex-1 flex overflow-hidden ">
                
                <div className="shrink-0">
                    <SidebarPrincipal theme = {theme}/>
                </div>

                <main className="flex-1 overflow-auto">
                    <Outlet context={{ theme: theme}}/>
                </main>    

                <div className="shrink-0">
                    <SidebarPanel 
                        moduleConfig = {SidebarPanelConfig} 
                        theme = {theme}
                    />
                </div>
            </div>

        </div>
    );
};