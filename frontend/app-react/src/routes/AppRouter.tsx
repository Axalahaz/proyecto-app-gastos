import { createBrowserRouter, Navigate } from "react-router-dom";
import { FilterProvider } from "@/context/FilterProvider";

import { layoutConfig } from "@/routes/layoutConfig";
import { AppLayout } from "@/shared/layouts/AppLayout";

import { BalancePage } from "@/modules/balance/pages/BalancePage";
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
                    <GastosPage />
                </GastosProvider>
            ),
                handle: { layout: layoutConfig.gastos},
            },
            { 
                path: "balance",  
                element: <BalancePage />,
                handle: { layout: layoutConfig.balance},
            },
            { 
                path: "future",  
                element: <FuturePage />,
                handle: { layout: layoutConfig.balance},
            },
            { 
                path: "*",
                element: <NotFoundPage /> 
            },
        ],
    },
    
]);