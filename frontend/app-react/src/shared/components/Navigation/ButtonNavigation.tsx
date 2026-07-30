import { useLocation } from "react-router-dom";
import { useState } from "react";

import { NavLink } from "react-router-dom";

import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme";

type Item = { 
    icon: React.ElementType; 
    label: string; 
    module: string; 
};

interface ButtonFooterProps extends ThemeProps {
    item: Item;
    sizeClass?: string;
}

export const ButtonSidebar = ({
    item,
    sizeClass,
    theme,
}: ButtonFooterProps) => {
    // Obtiene el nombre del modulo a redirigir
    const active = useLocation().pathname === item.module;
    
    // ---------------
    // Estado que indica accion de presion de boton
    const [pressed, setPressed] = useState(false);
    const isActive = active || pressed;
    
    return (
        <NavLink
            to={item.module}
            onPointerDown={() => setPressed(true)}
            onPointerUp={() => setPressed(false)}
            onPointerLeave={() => setPressed(false)}
            onPointerCancel={() => setPressed(false)}
            
            className={`${sizeClass} transition-all duration-100 
                ${isActive ? "pb-2" : ""}`
            }
            >
                <div className="flex flex-col items-center justify-center">
                    <item.icon className="w-7 h-7" />
                    <span>{item.label}</span>
                </div>
        </NavLink>
    );
};