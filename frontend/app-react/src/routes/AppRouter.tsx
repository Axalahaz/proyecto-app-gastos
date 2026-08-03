import { createBrowserRouter, Navigate } from "react-router-dom";
import { FilterProvider } from "@/context/FilterProvider";

import { moduleLayoutConfig } from "@/routes/config/moduleLayoutConfig";
import { appLayoutConfig } from "@/routes/config/appLayoutConfig";
import { AppLayout } from "@/shared/layouts/AppLayout";

import { BalancePage } from "@/modules/balance/pages/BalancePage";
import { ModuleLayout } from "@/shared/layouts/ModuleLayout";
import { GastosPage } from "@/modules/gastos/pages/GastosPage";
import { FuturePage } from "@/modules/futurePage";
import { NotFoundPage } from "@/shared/pages/NotFoundPage";

import { GastosProvider } from "@/modules/gastos/context/GastosProvider";

/** Version 2 */
export const AppRouter = createBrowserRouter([
    {
        path: "/",
        element: (
            <FilterProvider>
                <AppLayout />
            </FilterProvider>
        ),
        children: [
            { 
                index: true, 
                element: <Navigate to="balance" replace />
            },
            { 
                path: "gastos", 
                element: (
                    <GastosProvider>
                        <ModuleLayout/>
                    </GastosProvider>
                ),
                handle: {appLayout: appLayoutConfig.gastos},
                children: [
                    {
                        index: true,
                        element: <GastosPage />,
                        handle: {moduleLayout: moduleLayoutConfig.gastos},
                    },
                    { 
                        path: "*",
                        element: <NotFoundPage /> 
                    },
                ]
            },
            { 
                path: "balance",  
                element: <ModuleLayout />,
                handle: { appLayout: appLayoutConfig.balance},
                children: [
                    {
                        index: true,
                        element: <BalancePage />,
                        handle: {moduleLayout: moduleLayoutConfig.balance},
                    },
                    { 
                        path: "*",
                        element: <NotFoundPage /> 
                    },
                ]
            },
            { 
                path: "future",  
                element: <ModuleLayout />,
                handle: { appLayout: appLayoutConfig.balance},
                children: [
                    {
                        index: true,
                        element: <FuturePage />,
                        handle: {moduleLayout: moduleLayoutConfig.balance},
                    },
                    { 
                        path: "*",
                        element: <NotFoundPage /> 
                    },
                ]
            },
            { 
                path: "*",
                element: <NotFoundPage /> 
            },
        ],
    },
    
]);