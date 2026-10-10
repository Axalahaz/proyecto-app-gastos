import { Outlet, useMatches } from "react-router-dom";

import { defaultTheme } from "@/shared/theme";
import type { RouteHandle } from "@/routes/RouteHandle";

import {HeaderWave} from '@/shared/components/HeaderWave';


export const AppLayout = () => {
    // --------------
    // Obtiene el objeto de la ruta y le establece un id
    const matches = useMatches();

    // --------------
    // Establece si existen configuraciones para la ruta

    const config = matches
        .map(match => match.handle as RouteHandle | undefined)
        .find(handle => handle?.appLayout)
        ?.appLayout;

    if (!config) {
        return <Outlet />;
    }

    const {
        header: Header,
        sidebarPrincipal: SidebarPrincipal,
        filter: Filter,
        theme = defaultTheme,
    } = config ?? { theme: defaultTheme };

    return (
        <div className="relative flex flex-col h-dvh overflow-hidden">
        {/* Header */}
            <header className="shrink-0">
                <Header theme = {theme}/>
            </header>
        {/* Contenido */}
            <div className="min-h-0 flex-1 grid grid-cols-[180px_1fr] ">
                <div className="overflow-auto no-scrollbar">
                    <SidebarPrincipal theme = {theme}/>
                </div>
                <div className="min-h-0 flex flex-col overflow-auto no-scrollbar">
                    {Filter && (
                        <div className="shrink-0">
                            <Filter theme={theme} />
                        </div>
                    )}
                    <main className="flex-1">
                        <Outlet context={{ theme: theme}}/>
                    </main>    
                </div>
            </div>
        {/* Footer */}
            <footer className="pointer-events-none absolute bottom-0 z-10 w-full">
                <HeaderWave color={theme.colors[300]} />
            </footer>

        </div>
    );
};