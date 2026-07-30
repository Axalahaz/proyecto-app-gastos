import {HeaderWave} from '@/shared/components/HeaderWave';

import type { ThemeProps } from "@/shared/theme/themes";

import Box from '@mui/material/Box';
import AccountCircleOutlinedIcon from '@mui/icons-material/AccountCircle';

interface HeaderProps extends ThemeProps {
    title?: string;
    icon: React.ElementType,
    classIcon?: string;
}

// Valores por default
export const AppHeader = ({
    title = "HEADER",
    icon: Icon,
    classIcon = "",
    theme,
}: HeaderProps) => {
    return (
        <div 
            className="relative flex flex-col" 
            style={{ 
                background: theme.colors[700] ,
            }}
        >
            <div className="absolute bottom-0 left-0 w-full z-0">
                <HeaderWave color={theme.colors[300]} />
            </div>
            <div className="relative bottom-0 z-10 flex items-center py-1">
                <div className='flex-1 flex justify-center' >
                    <Icon className={`${classIcon}`}/>
                </div>
                <h1 
                    className='flex-[2] flex justify-center tracking-wide
                    font-semi text-base' 
                    style={{
                        WebkitTextStroke: `0.3px ${theme.colors[300]}`,
                        color: "white",
                        }}
                >
                    {title}
                </h1>
                <Box 
                    component="button" 
                    className="flex-1 flex justify-center"
                >
                    <AccountCircleOutlinedIcon  
                        sx={{
                            fontSize: 30,
                            fill: "transparent",
                            stroke: "white",
                            strokeWidth: 0.7,
                        }}
                    />
                </Box> 
            </div>
        </div>
    );
};