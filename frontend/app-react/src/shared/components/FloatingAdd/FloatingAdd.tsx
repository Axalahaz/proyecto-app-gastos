import { useRef, useState } from "react";
import Fab from "@mui/material/Fab";
import AddIcon from "@mui/icons-material/Add";

import type { ThemeProps } from "@/shared/theme/themes";

interface Props extends ThemeProps {
    active: boolean;
    openCalculadora: () => void;
    labelBoton: string;
    tituloBotom: string;
}

const BUTTON_SIZE = 56;
const MARGIN = 20;
const TITLE_HEIGHT = 25;

export const FloatingAdd = ({ 
    active,
    openCalculadora,
    labelBoton = "Agregar",
    tituloBotom = "NUEVO",
    theme,
}: Props) => {

    // -----------------
    const [position, setPosition] = useState(() => ({
        x: (window.innerWidth - BUTTON_SIZE) /1.8,
        y: window.innerHeight - BUTTON_SIZE - TITLE_HEIGHT - MARGIN ,
    }));

    const [arrastrando, setArrastrando] = useState(false);

    const dragStart = useRef({
        x: 0,
        y: 0,
    });

    const positionStart = useRef({
        x: 0,
        y: 0,
    });

    const moved = useRef(false);

    const handlePointerDown = (
        event: React.PointerEvent<HTMLButtonElement>
    ) => {

        event.currentTarget.setPointerCapture(event.pointerId);

        setArrastrando(true);

        moved.current = false;

        dragStart.current = {
            x: event.clientX,
            y: event.clientY,
        };

        positionStart.current = {
            x: position.x,
            y: position.y,
        };
    };

    const handlePointerMove = (
        event: React.PointerEvent<HTMLButtonElement>
    ) => {

        if (!arrastrando) return;

        const deltaX = event.clientX - dragStart.current.x;
        const deltaY = event.clientY - dragStart.current.y;

        if (Math.abs(deltaX) > 5 || Math.abs(deltaY) > 5) {
            moved.current = true;
        }

        const newX = Math.min(
            Math.max(0, positionStart.current.x + deltaX),
            window.innerWidth - BUTTON_SIZE
        );

        const newY = Math.min(
            Math.max(0, positionStart.current.y + deltaY),
            window.innerHeight - BUTTON_SIZE
        );

        setPosition({
            x: newX,
            y: newY,
        });
    };

    const handlePointerUp = (
        event: React.PointerEvent<HTMLButtonElement>
    ) => {

        if (!arrastrando) return;

        setArrastrando(false);

        event.currentTarget.releasePointerCapture(event.pointerId);

        // Si no se movió, fue un click
        if (!moved.current) {
            openCalculadora();
        } 
    };

    return (
        <div
            className="flex flex-col items-center gap-2"
            style={{
                position: "fixed",

                left: `${position.x}px`,
                top: `${!moved.current && active
                    ? `${position.y-32}px`
                    : `${position.y}px`
                } `,

                zIndex: 1300,

                width: BUTTON_SIZE,

                cursor: arrastrando
                    ? "grabbing"
                    : "grab",

                userSelect: "none",
                touchAction: "none",
            }}
        >
            {active &&
                <h1
                    className="whitespace-nowrap tracking-widest"
                    style={{
                        color: theme.colors[700],
                        fontWeight: 700,
                        textShadow: `
                            0 -1px 2px ${theme.colors[300]},
                            0 1px 5px ${theme.colors[300]},
                            0 2px 6px ${theme.colors[300]}
                        `,
                    }}
                >
                    {tituloBotom}
                </h1>
            }
            <Fab
                aria-label={labelBoton}
                onPointerDown={handlePointerDown}
                onPointerMove={handlePointerMove}
                onPointerUp={handlePointerUp}
                sx={{
                    width: BUTTON_SIZE,
                    height: BUTTON_SIZE,

                    backgroundColor: theme.colors[700],
                    color: theme.colors[100],
                    boxShadow: `
                        0 1px 8px ${theme.colors[300]},
                        0 3px 10px ${theme.colors[300]}
            `,

                    cursor: arrastrando
                        ? "grabbing"
                        : "grab",

                    userSelect: "none",
                    touchAction: "none",

                    transition: arrastrando
                        ? "none"
                        : "transform 0.2s ease",

                    "&:hover": {
                        backgroundColor: theme.colors[900],
                        boxShadow: `0 3px 10px ${theme.colors[100]}`,
                    },

                    "&:active": {
                        transform: arrastrando
                            ? "scale(1)"
                            : "scale(0.9)",
                    },
                }}
            >
                <AddIcon />
            </Fab>
        </div>
    );
};