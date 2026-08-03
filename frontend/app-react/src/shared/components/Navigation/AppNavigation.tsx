import type { ThemeProps } from "@/shared/theme/themes";

import { ButtonSidebar } from "@/shared/components/Navigation/ButtonNavigation";
import { bottomSidebarConfig } from "@/shared/components/Navigation/bottomNavigationConfig";
import { useState } from "react";

import gastos  from "@/assets/images/gastos.png";
//import ahorros from "@/assets/images/ahorros.png";
import ingresos from "@/assets/images/ingresos.png";
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
    const [active, setActive] = useState<string>("");

    console.log("---img:",active)
    return (
        <div className={`h-full px-2 py-5 flex flex-col gap-3`}>
            <div className="w-35 h-30 object-contain">
                {active === "Gastos" && <img src={gastos} alt="img_gastos" className="w-full h-full "/>}
                {active === "Balance" && <img src={ingresos} alt="img_balance" className="w-full h-full"/>}
            </div>
            <div className="flex flex-col gap-2">
                {bottoms.map(([key, item]) =>
                    <ButtonSidebar
                        key={`button-${key}`}
                        item={item}
                        sizeClass={sizeClass}
                        setActive={setActive}
                        theme={theme}
                        />
                    )}
            </div>
        </div>
    );
};