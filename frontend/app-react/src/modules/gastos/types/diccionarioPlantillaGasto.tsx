import Agua from "@/assets/iconsGastos/agua.svg";
import Gas from "@/assets/iconsGastos/gas.svg";
import Luz from "@/assets/iconsGastos/luz.svg";
import Internet from "@/assets/iconsGastos/internet.svg";
import Celular from "@/assets/iconsGastos/celular.svg";
import Carniceria from "@/assets/iconsGastos/carniceria.svg";
import Verduleria from "@/assets/iconsGastos/verduleria.svg";
import Panaderia from "@/assets/iconsGastos/panaderia.svg";
import BoletoTransporte from "@/assets/iconsGastos/boleto_transporte.svg";
import Gym from "@/assets/iconsGastos/gym.svg";
import Image from "@/assets/image.svg";

import { TIPO_OBJETO, type tipoObjeto } from "@/shared/types/typesApp";
import { defaultTheme } from "@/shared/theme";

type PropsPlantillaGastoIcons = {
    iconPlantilla: string; 
    bgPlantilla: string; 
    borderPlantilla: string; 
}

export const plantillaGastoIcons = {
    Agua: { 
        iconPlantilla: Agua,
        bgPlantilla: "#f6fdf1",
        borderPlantilla: "#179b47"
    },
    Gas: { 
        iconPlantilla: Gas, 
        bgPlantilla: "#f8f8de", 
        borderPlantilla: "#ffff00"
    },
    Luz: { 
        iconPlantilla: Luz, 
        bgPlantilla: "#fdf1ee", 
        borderPlantilla: "#47050559"
    },
    Internet: { 
        iconPlantilla: Internet, 
        bgPlantilla: "#fff0da", 
        borderPlantilla: "#492b00"
    },
    Celular: { 
        iconPlantilla: Celular, 
        bgPlantilla: "#fff0da", 
        borderPlantilla: "#492b00"
    },
    Carniceria: { 
        iconPlantilla: Carniceria, 
        bgPlantilla: "#fff0da", 
        borderPlantilla: "#492b00"
    },
    Verduleria: { 
        iconPlantilla: Verduleria, 
        bgPlantilla: "#fff0da", 
        borderPlantilla: "#492b00"
    },
    Panaderia: { 
        iconPlantilla: Panaderia, 
        bgPlantilla: "#fff0da", 
        borderPlantilla: "#492b00"
    },
    BoletoTransporte: { 
        iconPlantilla: BoletoTransporte,  
        bgPlantilla: "#eeffee", 
        borderPlantilla: "#3AB0FF"
    },
    Gym: { 
        iconPlantilla: Gym, 
        bgPlantilla: "#fff0da", 
        borderPlantilla: "#492b00"
    },
} as const satisfies Record<string, PropsPlantillaGastoIcons>;

export type PlantillaGastoName = keyof typeof plantillaGastoIcons;

export const getPlantillaGastoConfig = (
    descripcion: string,
    tipo?: tipoObjeto,
): PropsPlantillaGastoIcons | null => {

    if(tipo) {
        if (tipo === TIPO_OBJETO.USUARIO) {
            return {
                iconPlantilla: Image,
                bgPlantilla: defaultTheme.colors[100], 
                borderPlantilla: defaultTheme.colors[500], 
            };
        }
    
        else if (tipo === TIPO_OBJETO.SISTEMA) {
            return plantillaGastoIcons[descripcion as PlantillaGastoName] ?? null;
        }
    }

    return null;
};