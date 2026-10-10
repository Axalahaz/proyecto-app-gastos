import type { ThemeProps } from "@/shared/theme/themes"
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto"
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";
import { SelectorPlantilla } from "@/modules/gastos/components/ModuleCalculadora/SelectorPlantilla";
import { getPlantillaGastoConfig } from "@/modules/gastos/types/diccionarioPlantillaGasto";
import { getCategoriaConfig } from "@/modules/gastos/types/diccionarioCategorias";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";

interface Props extends ThemeProps {
    categoria: CategoriaGasto | null;
    nombreCategoria: string;
    nombrePlantilla: string;
    plantilla: PlantillaGasto | null;
    plantillas: PlantillaGasto[];
    plantillaSeleccionadaSelector: PlantillaGasto | null;
    plantillaSelector: (plantillaGasto: PlantillaGasto | null) => void;
    mostrarSelector: boolean;
    recurrenteSeleccionado: GastoRecurrente | null;
}

export const GastoInfo = ({
    categoria,
    nombreCategoria,
    plantilla,
    nombrePlantilla,
    plantillas,
    plantillaSeleccionadaSelector,
    plantillaSelector,
    mostrarSelector,
    recurrenteSeleccionado,
    theme,
}:Props) => {

    // -----------------------------        
    // CONFIGURACIONES

    // * Plantilla
    const plantillaConfig = getPlantillaGastoConfig(nombrePlantilla, plantilla?.tipo);
    const {
        iconPlantilla,
        bgPlantilla,
        borderPlantilla,
    } = plantillaConfig ?? {};
    
    // * Categoria
    const categoriaConfig = getCategoriaConfig(nombreCategoria, categoria?.tipo);
    const { 
        iconCategoria, 
        bgCategoria,
        borderCategoria,
    } = categoriaConfig ?? {};

    // -------------------------
    return (
        <div className="flex items-center justify-between">
            {/** Contenedor categoria */}
            {iconCategoria  && (
                <div className="flex justify-center items-center gap-2">
                    <div 
                        className="w-12 h-12 rounded-full border p-[6px] overflow-hidden"
                        style={{
                            borderColor: borderCategoria,
                            backgroundColor: bgCategoria,
                        }}
                    >
                        <img
                            src={iconCategoria}
                            alt={nombreCategoria}
                            className="w-full h-full object-contain"
                        />
                    </div>
                    <div className="flex flex-col justify-center  tracking-wide">
                        <h1 className="text-base  leading-none">
                            Categoria
                        </h1>
                        <p 
                            className="font-beiruti tracking-wide"
                            style={{color: theme.colors[900]}}
                        >
                            {nombreCategoria}
                        </p>
                    </div>
                </div>
            )}
            {/** Contenedor Selector plantillas */}
            {!mostrarSelector && 
                <SelectorPlantilla
                    plantillas={plantillas}
                    plantillaSeleccionada={plantillaSeleccionadaSelector}
                    seleccionarPlantilla={plantillaSelector}
                    theme={theme}
                />
            }
            {/** Contenedor Gasto Recurrente */}
            {iconPlantilla && (
                <div className="flex items-center justify-center gap-3 px-10">
                    <div 
                        className="w-10 h-10  rounded-full border
                        flex justify-center items-center overflow-hidden"
                        style={{
                            backgroundColor: bgPlantilla,
                            borderColor: borderPlantilla,
                        }}
                    >
                        <img
                            src={iconPlantilla}
                            alt={recurrenteSeleccionado?.descripcion 
                                || plantilla?.descripcion || ""}
                            className="w-full h-full p-[6px] object-contain"
                        />
                    </div>
                    <div className="flex flex-col justify-center gap-1 tracking-wide">
                        <h1 className=" text-base leading-none">
                            {recurrenteSeleccionado ? "Gasto Recurrente" : "Plantilla"}
                        </h1>
                        <p 
                            className="font-beiruti leading-none"
                            style={{color: theme.colors[900]}}
                        >
                            {nombrePlantilla}
                        </p>
                    </div>
                </div>
            )}
        </div>
    )
}