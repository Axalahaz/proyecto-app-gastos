import { Outlet, useMatches } from "react-router-dom";
import type { RouteHandle } from "@/routes/RouteHandle";

import { defaultTheme } from "@/shared/theme";

export const ModuleLayout = () => {
    // --------------
    // Obtiene el objeto de la ruta y le establece un id
    const matches = useMatches();

    // --------------
    // Establece si existen configuraciones para la ruta
    const config = matches
        .map(match => match.handle as RouteHandle | undefined)
        .find(handle => handle?.moduleLayout)
            ?.moduleLayout;
        

    if (!config) {
        return <Outlet />;
    }

    const {
        sidebarPanel: SidebarPanel,
        theme = defaultTheme,
    } = config ?? { theme: defaultTheme };

    return (
        <div className="grid grid-cols-[1fr_350px] ">

            <main className="no-scrollbar overflow-auto px-2">
                <Outlet context={{ theme }} />
            </main>

            <div className="no-scrollbar overflow-auto px-2">
                <SidebarPanel theme={theme} />
            </div>

        </div>
    );
};