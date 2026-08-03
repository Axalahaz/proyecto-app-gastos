import { useLocation } from "react-router-dom";
import { useState } from "react";

import { NavLink } from "react-router-dom";

import type { ThemeProps } from "@/shared/theme/themes";

type Item = { 
    icon: React.ElementType; 
    label: string; 
    module: string; 
};

interface ButtonFooterProps extends ThemeProps {
    item: Item;
    sizeClass?: string;
    setActive: (i:string) => void;
}

export const ButtonSidebar = ({
    item,
    sizeClass,
    setActive,
    theme,
}: ButtonFooterProps) => {
    // Obtiene el nombre del modulo a redirigir
    const active = useLocation().pathname === item.module;
    
    // ---------------
    // Estado que indica accion de presion de boton
    const [pressed, setPressed] = useState(false);
    const isActive = active || pressed;
    if(isActive) {
        setActive(item.label)
    }
    // !! tengo que agregarle efecto de seleccion
    return (
        <NavLink
            to={item.module}
            onPointerDown={() => setPressed(true)}
            onPointerUp={() => setPressed(false)}
            onPointerLeave={() => setPressed(false)}
            onPointerCancel={() => setPressed(false)}
            
            className={`${sizeClass} w-full rounded-r-full
            flex items-center gap-3 px-2
            transition-all duration-100 active:scale-120
                ${isActive ? "-ms-2 ps-10" : ""}`
            }
            style={{
                background: `${isActive ? theme.colors[500] : ""}`,
                color: `${isActive ? theme.colors[100] : ""}`,
                boxShadow: `${isActive ? `2px 2px 3px ${theme.colors[300]}` : ""}`,
            }}
        >
            <div>
                <item.icon className={`${isActive ? "w-9 h-9" : "w-7 h-7"}`} />
            </div>
            <p>{item.label}</p>
        </NavLink>
    );
};