import { createBrowserRouter, Navigate } from "react-router-dom";
import { FilterProvider } from "@/context/FilterProvider";

import { appLayoutConfig } from "@/routes/config/appLayoutConfig";
import { AppLayout } from "@/shared/layouts/AppLayout";

import { BalancePage } from "@/modules/balance/pages/BalancePage";
import { GastosPage } from "@/modules/gastos/pages/GastosPage";
import { FuturePage } from "@/modules/futurePage";
import { NotFoundPage } from "@/shared/pages/NotFoundPage";

import { GastosProvider } from "@/modules/gastos/context/GastosProvider";
import { GastosProviderMock } from "@/mocks/contextMock/GastosProviderMock";

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
                    <GastosProviderMock>
                    {/*<GastosProvider>*/}
                        <GastosPage/>
                    {/*</GastosProvider>*/}
                    </GastosProviderMock>
                ),
                handle: {appLayout: appLayoutConfig.gastos},
            },
            { 
                path: "balance",  
                element: <BalancePage />,
                handle: { appLayout: appLayoutConfig.balance},
            },
            { 
                path: "future",  
                element: <FuturePage />,
                handle: { appLayout: appLayoutConfig.balance},
            },
            { 
                path: "*",
                element: <NotFoundPage /> 
            },
        ],
    },
    
]);