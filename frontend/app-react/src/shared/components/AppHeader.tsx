import type { ThemeProps } from "@/shared/theme/themes";

import Box from '@mui/material/Box';
import AccountCircleOutlinedIcon from '@mui/icons-material/AccountCircle';

interface HeaderProps extends ThemeProps {
    title?: string;
}

// Valores por default
export const AppHeader = ({
    title = "HEADER",
    theme,
}: HeaderProps) => {
    return (
        <div 
            className="grid grid-cols-[180px_1fr_180px] 
            py-1 items-center text-center text-base" 
            style={{ 
                background: theme.colors[700] ,
            }}
        >
            <h1 
                className='tracking-wide font-semi text-base' 
                style={{
                    WebkitTextStroke: `0.3px ${theme.colors[300]}`,
                    color: "white",
                    }}
            >
                {"By JBYCode"} <span className="tracking-wide">{"{}"}</span>
            </h1>
            <h1 
                className='tracking-wide font-semi' 
                style={{
                    WebkitTextStroke: `0.3px ${theme.colors[300]}`,
                    color: "white",
                    }}
            >
                {title}
            </h1>
            <div
                className="flex justify-center items-center"
            >
                <button 
                    className="flex items-center gap-2"
                >
                    <p style={{color: theme.colors[100]}}>Cuenta</p>
                    <AccountCircleOutlinedIcon  
                        sx={{
                            fontSize: 30,
                            fill: "transparent",
                            stroke: "white",
                            strokeWidth: 1.4,
                        }}
                    />
                </button> 
            </div>
        </div>
    );
};