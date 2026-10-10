import type { ThemeProps } from "@/shared/theme/themes";
import type { PlantillaGasto } from "../../dto/plantillaGasto/PlantillaGasto";
import { getPlantillaGastoConfig, } from "../../types/diccionarioPlantillaGasto";
import FormControl from "@mui/material/FormControl";
import Select, { type SelectChangeEvent } from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import { useGastos } from "../../context/useGastos";

interface Props extends ThemeProps {
    plantillas: PlantillaGasto[];
    plantillaSeleccionada: PlantillaGasto | null;
    seleccionarPlantilla: (plantillaGasto: PlantillaGasto | null) => void;
}

export const SelectorPlantilla = ({
    plantillas, 
    plantillaSeleccionada,
    seleccionarPlantilla,
    theme, 
}: Props) => {

    // -----------------------------
    // * Si la categoria no tiene plantillas
    if(plantillas.length === 0) {
        return <div><h1>No hay plantilla</h1></div>;
    }
    
    // -----------------------------
    const handleChange = (
        event: SelectChangeEvent<number>
    ) => {
        const plantillaId = event.target.value;

        const plantilla = plantillas.find(
            p => p.id === plantillaId
        ) ?? null;

        seleccionarPlantilla(plantilla);
    };
    
    // -----------------------------
    return (
        <FormControl 
            variant="standard" 
            sx={{ 
                mt: -5, 
                mb: -5, 
                mr: -1,
                minWidth: 100,
                }}
        >
            <Select<number>
                labelId="plantilla-select-label"
                id="plantilla-select"
                value={plantillaSeleccionada?.id ?? 0}
                onChange={handleChange}
            >
                <MenuItem value={0}>
                    <em>Elegir Plantilla</em>
                </MenuItem>
                {/* Codigo para configuracion */}
                {plantillas.map(p => {
                    const config = (getPlantillaGastoConfig(p.descripcion,p?.tipo));
                    const {iconPlantilla} = config ?? {};

                    return (
                        <MenuItem 
                            key={p.id} 
                            value={p.id}
                            sx={{
                                "&:hover": {
                                    backgroundColor: theme.colors[300],
                                    color: theme.colors[700],
                                },
                                "&.Mui-selected": {
                                    backgroundColor: theme.colors[700],
                                    color: theme.colors[100],
                                },
                                "&.Mui-selected:hover": {
                                    backgroundColor: theme.colors[300],
                                },
                            }}
                        >
                            <div className="flex items-center gap-2 text-sm">
                                {iconPlantilla && (
                                    <img
                                        src={iconPlantilla}
                                        alt={p.descripcion}
                                        className="w-7 object-contain"
                                    />
                                )}
                                <span className="font-beiruti text-base">{p.descripcion}</span>
                            </div>
                        </MenuItem>
                    );
                })}
            </Select>
        </FormControl>
    );
}