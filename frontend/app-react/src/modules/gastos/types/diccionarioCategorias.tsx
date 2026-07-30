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
import TransporteAuto from "@/assets/iconsCategorias/transporte_auto.svg";
import TransporteBus from "@/assets/iconsCategorias/transporte_bus.svg";
import TransporteMoto from "@/assets/iconsCategorias/transporte_moto.svg";
import TransporteTren from "@/assets/iconsCategorias/transporte_tren.svg";
import Viajes from "@/assets/iconsCategorias/viajes.svg";

export const categoriaIcons = {
    hogar: { 
        icon: Hogar, label: "Hogar", 
        bg: "#f6fdf1", border: "#179b47"
    },
    servicios: { 
        icon: Servicios, label: "Servicios", 
        bg: "#f8f8de", border: "#ffff00"
    },
    comida: { 
        icon: Compras, label: "Comida", 
        bg: "#fdf1ee", border: "#47050559"
    },
    transporteAuto: { 
        icon: TransporteAuto, label: "Transporte", 
        bg: "#fff0da", border: "#492b00"
    },
    transporteMoto: { 
        icon: TransporteMoto, label: "Transporte", 
        bg: "#fff0da", border: "#492b00"
    },
    transporteTren: { 
        icon: TransporteTren, label: "Transporte", 
        bg: "#fff0da", border: "#492b00"
    },
    transporteBus: { 
        icon: TransporteBus, label: "Transporte", 
        bg: "#fff0da", border: "#492b00"
    },
    salud: { 
        icon: Salud, label: "Salud", 
        bg: "#fff0da", border: "#492b00"
    },
    educacion: { 
        icon: Educacion, label: "Educación", 
        bg: "#eeffee", border: "#3AB0FF"
    },
    entretenimiento: { 
        icon: Entretenimiento, label: "Entretenimiento", 
        bg: "#fff0da", border: "#492b00"
    },
    mascota: { 
        icon: Mascota, label: "Mascota", 
        bg: "#fff0da", border: "#9b5b02"
    },
    trabajo: { 
        icon: Trabajo, label: "Trabajo", 
        bg: "#fff0da", border: "#492b00"
    },
    plataformas: { 
        icon: Plataformas, label: "Plataformas", 
        bg: "#fff0da", border: "#492b00"
    },
    salidas: { 
        icon: Hogar, label: "Salidas", 
        bg: "#fff0da", border: "#492b00"
    },
    viajes: { 
        icon: Viajes, label: "Viajes", 
        bg: "#fff0da", border: "#492b00"
    },
    restaurante: { 
        icon: Restaurante, label: "Restaurante", 
        bg: "#fff0da", border: "#492b00"
    },
    regalos: { 
        icon: Regalos, label: "Regalos", 
        bg: "#fff0da", border: "#492b00"
    },
    bar: { 
        icon: Bar, label: "Bar", 
        bg: "#ffffea", border: "#575702"
    },
} as const satisfies Record<string, {
    icon: string; 
    label: string 
    bg: string 
    border: string 
}>;

export type CategoriaName = keyof typeof categoriaIcons;

export const getCategoriaConfig = (nombre: string) => {
    return (
        categoriaIcons[nombre as keyof typeof categoriaIcons] ?? {
            icon: "📦",
            label: nombre,
            bg: "#f5f5f5",
            border: "#bdbdbd",
        }
    );
};