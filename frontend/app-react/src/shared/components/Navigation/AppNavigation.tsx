import type { ThemeProps } from "@/shared/theme/themes";

import { ButtonSidebar } from "@/shared/components/Navigation/ButtonNavigation";
import { bottomSidebarConfig } from "@/shared/types/typeBottomNavigationConfig";

import gastos  from "@/assets/images/gastos.png";
//import ahorros from "@/assets/images/ahorros.png";
import ingresos from "@/assets/images/ingresos.png";
import { useLocation } from "react-router-dom";
interface SidebarProps extends ThemeProps{
    title?: string;
    sizeClass?: string;
}

export const AppNavigation = ({ 
    sizeClass = "w-14 h-14", 
    theme,
}: SidebarProps) => {

    // ---------------
    // Botones disponibles
    const bottoms = Object.entries(bottomSidebarConfig);

    // ---------------
    // Boton seleccionado
    const location = useLocation();
    const activeItem = bottoms.find(
        ([, item]) => item.module === location.pathname
    );
    const activeLabel = activeItem?.[1].label;

    return (
        <div className={`h-dvh px-2 py-5 flex flex-col gap-3`}>
            <div className="w-35 h-30 object-contain">
                {activeLabel === "Gastos" && <img src={gastos} alt="img_gastos" className="w-full h-full "/>}
                {activeLabel === "Balance" && <img src={ingresos} alt="img_balance" className="w-full h-full"/>}
            </div>
            <div className="flex flex-col gap-2">
                {bottoms.map(([key, item]) =>
                    <ButtonSidebar
                        key={`button-${key}`}
                        item={item}
                        sizeClass={sizeClass}
                        theme={theme}
                        />
                    )}
            </div>
        </div>
    );
};