import { Outlet, useMatches } from "react-router-dom";

import { defaultTheme } from "@/shared/theme";
import type { RouteHandle } from "@/routes/RouteHandle";

import {HeaderWave} from '@/shared/components/HeaderWave';


export const AppLayout = () => {
    console.log("---appLayout---") //!!
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
        theme = defaultTheme,
    } = config ?? { theme: defaultTheme };

    console.log("---appLayout config: ", config) //!!
    
    return (
        <div className="flex flex-col h-screen relative">

            <header className="w-full">
                <Header theme = {theme}/>
            </header>

            <div className="flex-1 grid grid-cols-[180px_1fr] overflow-hidden">
                
                <div className="no-scrollbar overflow-auto">
                    <SidebarPrincipal theme = {theme}/>
                </div>

                <main className="no-scrollbar overflow-auto">
                    <Outlet context={{ theme: theme}}/>
                </main>    

            </div>

            <footer className="w-full absolute bottom-0 z-10 ">
                <HeaderWave color={theme.colors[300]} />
            </footer>

        </div>
    );
};