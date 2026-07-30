import type { ThemeProps } from "@/shared/theme/themes";

import { ButtonSidebar } from "@/shared/components/Navigation/ButtonNavigation";
import { bottomSidebarConfig } from "@/shared/components/Navigation/bottomNavigationConfig";

interface SidebarProps extends ThemeProps{
    title?: string;
    sizeClass?: string;
}

export const AppNavigation = ({ 
    title = "", 
    sizeClass = "w-14 h-14", 
    theme,
}: SidebarProps) => {

    // ---------------
    // Botones disponibles
    const bottoms = Object.entries(bottomSidebarConfig);

    return (
        <div 
            className={`flex flex-col rounded-t-[28px] shadow -my-1`}
            style={{
                background: theme.colors[100],
                boxShadow: `0 0px 5px ${theme.colors[300]}`
            }}
        >
            <div className="px-2 flex items-center justify-between">
                {bottoms.map(([key, item]) =>
                        <ButtonSidebar
                            key={`button-${key}`}
                            item={item}
                            sizeClass={sizeClass}
                            theme={theme}
                        />
                    )}
            </div>
            <h1>{title}</h1>
        </div>
    );
};