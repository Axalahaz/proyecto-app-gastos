import Hogar from "@/assets/iconsCategorias/hogar.svg";
import Compras from "@/assets/iconsCategorias/compras.svg";
import Mascota from "@/assets/iconsCategorias/mascota.svg";
import Bar from "@/assets/iconsCategorias/bar.svg";
import Educacion from "@/assets/iconsCategorias/educacion.svg";
import Entretenimiento from "@/assets/iconsCategorias/entretenimiento.svg";
import Plataformas from "@/assets/iconsCategorias/plataformas.svg";
import Regalos from "@/assets/iconsCategorias/regalos.svg";
import Restaurante from "@/assets/iconsCategorias/restaurante.svg";
import Salud from "@/assets/iconsCategorias/salud.svg";
import Servicios from "@/assets/iconsCategorias/servicios.svg";
import Trabajo from "@/assets/iconsCategorias/trabajo.svg";
import Transporte from "@/assets/iconsCategorias/transporte.svg";
import Viajes from "@/assets/iconsCategorias/viajes.svg";
import Ropa from "@/assets/iconsCategorias/ropa.svg";
import Image from "@/assets/image.svg";

import { TIPO_OBJETO, type tipoObjeto } from "@/shared/types/typesApp";
import { defaultTheme } from "@/shared/theme";

type PropsCategoriaIcons = {
    iconCategoria: string; 
    bgCategoria: string;
    borderCategoria: string; 
}

// --------------------------------------
// Configuración estática
export const categoriaIcons = {
    Hogar: { 
        iconCategoria: Hogar,
        bgCategoria: "#f6fdf1", borderCategoria: "#6ccc8f"
    },
    Servicios: { 
        iconCategoria: Servicios, 
        bgCategoria: "#f8f8de", borderCategoria: "#c9c903"
    },
    Compras: { 
        iconCategoria: Compras, 
        bgCategoria: "#fdf1ee", borderCategoria: "#47050559"
    },
    Transporte: { 
        iconCategoria: Transporte, 
        bgCategoria: "#fff0da", borderCategoria: "#df8d1a"
    },
    Salud: { 
        iconCategoria: Salud, 
        bgCategoria: "#feffda", borderCategoria: "#caa45e"
    },
    Educacion: { 
        iconCategoria: Educacion, 
        bgCategoria: "#eeffee", borderCategoria: "#3AB0FF"
    },
    Entretenimiento: { 
        iconCategoria: Entretenimiento, 
        bgCategoria: "#fff0da", borderCategoria: "#492b00"
    },
    Mascota: { 
        iconCategoria: Mascota, 
        bgCategoria: "#fff0da", borderCategoria: "#9b5b02"
    },
    Trabajo: { 
        iconCategoria: Trabajo, 
        bgCategoria: "#fff0da", borderCategoria: "#492b00"
    },
    Plataformas: { 
        iconCategoria: Plataformas,
        bgCategoria: "#ffd6b4", borderCategoria: "#dd7871"
    },
    Viajes: { 
        iconCategoria: Viajes,
        bgCategoria: "#fff0da", borderCategoria: "#492b00"
    },
    Restaurante: { 
        iconCategoria: Restaurante, 
        bgCategoria: "#fff0da", borderCategoria: "#492b00"
    },
    Regalos: { 
        iconCategoria: Regalos,
        bgCategoria: "#fff0da", borderCategoria: "#492b00"
    },
    Bar: { 
        iconCategoria: Bar, 
        bgCategoria: "#ffffea", borderCategoria: "#575702"
    },
    Ropa: { 
        iconCategoria: Ropa, 
        bgCategoria: "#ffffea", borderCategoria: "#575702"
    },
} as const satisfies Record<string, PropsCategoriaIcons>;

export type CategoriaName = keyof typeof categoriaIcons;

// --------------------------------------
// Resolución de configuración
export const getCategoriaConfig = (
    nombre: string,
    tipo?: tipoObjeto,
): PropsCategoriaIcons | null  => {

    if (tipo) {
        if (tipo === TIPO_OBJETO.USUARIO) {
            return {
                iconCategoria: Image,
                bgCategoria: defaultTheme.colors[100], 
                borderCategoria: defaultTheme.colors[500], 
            }
        }
        else if (tipo === TIPO_OBJETO.SISTEMA) {
            return categoriaIcons[nombre as CategoriaName ] ?? null;
        }
    }

    return null;
};