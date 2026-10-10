import { useEffect, useMemo, useState } from "react";

import { ButtonCalendario } from "@/shared/components/calculadora/ButtonCalendario";
import { MoneyCalculatorContenedor } from "@/shared/components/calculadora/MoneyCalculatorContenedor";
import { SelectorPlantilla } from "@/modules/gastos/components/ModuleCalculadora/SelectorPlantilla";
import { Teclado } from "@/shared/components/calculadora/Teclado";

import { useCalculadora } from "@/shared/hooks/useCalculadora";
import { useGastos } from "@/modules/gastos/context/useGastos";
import { useFilter } from "@/hooks/useFilter";

import { getCategoriaConfig } from "@/modules/gastos/types/diccionarioCategorias";
import { getPlantillaGastoConfig } from "@/modules/gastos/types/diccionarioPlantillaGasto";

import type { ThemeProps } from "@/shared/theme/themes";

import { ModalRecurrencia } from "@/modules/gastos/components/ModuleCalculadora/recurrencia/ModalRecurrencia";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import { ResumenRecurrencia } from "@/modules/gastos/components/ModuleCalculadora/recurrencia/ResumenRecurrencia";

import MenuBookIcon from '@mui/icons-material/MenuBook';

import { IconOk } from "@/shared/icons/IconOk";

import { Input } from "@/shared/components/calculadora/Input";
import type { FormularioGasto } from "@/modules/gastos/dto/gasto/FormularioGasto";

import mapGastoService from "@/modules/gastos/services/mappers/mapGastoService";
import mapGastoRecurrenteService from "@/modules/gastos/services/mappers/mapGastoRecurrenteService";
import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import { gastoServiceWrite } from "@/modules/gastos/services/gasto/gastoServiceWrite";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import { gastoRecurrenteServiceRead } from "@/modules/gastos/services/gastoRecurrente/gastoRecurrenteServiceRead";
import { gastoRecurrenteServiceWrite } from "@/modules/gastos/services/gastoRecurrente/gastoRecurrenteServiceWrite";
import { FRECUENCIA } from "@/modules/gastos/types/typeGastos";
import { defaultTheme } from "@/shared/theme";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";

interface Props extends ThemeProps {
    gastoSeleccion: Gasto;
    gastoRecurrenteSeleccion: GastoRecurrente;
    categoriaGastoSeleccion: CategoriaGasto
}

export const CalculadoraGastos = ({
    theme,
}: Props) => {

    // -----------------------------
    // CALCULADORA
    const {
        total,
        expresion,
        borrar,
        limpiar,
        presionarTecla
    } = useCalculadora();

    const { selectedDate } = useFilter();

    // -----------------------------
    // CONTEXTO
    const { 
        gastoSeleccionado,
        modificarGasto,
        registrarGasto,

        categoriaGastoSeleccionada, 
        categoriasGastos,

        gastoRecurrenteSeleccionado,
        modificarGastoRecurrente,
        registrarGastoRecurrente,

        plantillasGastos,
    } = useGastos();

    // ----------------------------
    // PLANTILLA: activacion por GASTO o GASTO RECURRENTE
    const plantillaSeleccionada = useMemo(() => {

        if (gastoRecurrenteSeleccionado) {
            return plantillasGastos.find(
                p => p.id === gastoRecurrenteSeleccionado.plantillaId
            ) ?? null;
        }
        if (gastoSeleccionado) {
            return plantillasGastos.find(
                p => p.id === gastoSeleccionado.plantillaId
            ) ?? null;
        }
        return null;

    }, [
        gastoRecurrenteSeleccionado,
        gastoSeleccionado,
        plantillasGastos
    ]);

    const nombrePlantilla = plantillaSeleccionada?.descripcion
        ?? gastoRecurrenteSeleccionado?.descripcion 
        ?? gastoSeleccionado?.descripcion;
    
    // -----------------------------
    // CATEGORIA
    const categoriaSeleccionada = useMemo(() => {

        if (categoriaGastoSeleccionada) {
            return categoriaGastoSeleccionada;
        }
        if (plantillaSeleccionada) {
            return categoriasGastos.find(
                c => c.id === plantillaSeleccionada.categoriaGastoId
            ) ?? null;
        }
        if (gastoSeleccionado) {
            return categoriasGastos.find(
                c => c.id === gastoSeleccionado.categoriaGastoId
            ) ?? null;
        }

        return null;
    }, [
        categoriaGastoSeleccionada,
        plantillaSeleccionada,
        categoriasGastos
    ]);

    const plantillasPorCategoria = useMemo(() => {
        if (!categoriaGastoSeleccionada) {
            return [];
        }

        return plantillasGastos.filter(
            p => p.categoriaGastoId === categoriaGastoSeleccionada.id
        ) ?? null;
    }, [plantillasGastos, categoriaGastoSeleccionada]);

    const nombreCategoria = categoriaSeleccionada?.nombre ?? "";

    // -----------------------------        
    // CONFIGURACIONES

    // * Plantilla
    const plantillaConfig = getPlantillaGastoConfig(nombrePlantilla ?? "", plantillaSeleccionada?.tipo);
    const {
        iconPlantilla,
        bgPlantilla = defaultTheme.colors[100],
        borderPlantilla = defaultTheme.colors[500],
    } = plantillaConfig ?? {};
    
    // * Categoria
    const categoriaConfig = getCategoriaConfig(nombreCategoria, categoriaGastoSeleccionada?.tipo);
    const { 
        iconCategoria, 
        bgCategoria,
        borderCategoria,
    } = categoriaConfig ?? {};
    
    //------------------
    // FORMULARIO DATOS
    const descripcionInicial =
        gastoSeleccionado?.descripcion ??
        gastoRecurrenteSeleccionado?.descripcion ??
        "";

    const [formGasto, setFormGasto] = useState<FormularioGasto>({
        monto: gastoSeleccionado?.monto ?? total,
        descripcion: descripcionInicial,
        estado: gastoSeleccionado?.estado,
    });

    const [campoInput, setCampoInput] = useState<string>(descripcionInicial);

    const [error, setError] = useState<string | null>(null);

    const limpiarError = () => {
        setError(null);
    };
    
    //------------------
    // MODAL RECURRENCIA
    const [esRecurrente, setEsRecurrente] = useState(false);
    const [openRecurrencia, setOpenRecurrencia] = useState(false);

    useEffect(() => {
        if (!gastoRecurrenteSeleccionado && esRecurrente) {
            setFormGasto(prev => ({
                ...prev,
                diaVencimiento: 1,
                frecuencia: FRECUENCIA.MENSUAL,
            }));
            return;
        } 
    }, [esRecurrente])
    
    useEffect(() => {
        if(gastoRecurrenteSeleccionado) {
            setEsRecurrente(true);
            setFormGasto(prev => ({
                ...prev,
                diaVencimiento: gastoRecurrenteSeleccionado.diaVencimiento,
                mesVencimiento: gastoRecurrenteSeleccionado.mesVencimiento ?? undefined,
                frecuencia: gastoRecurrenteSeleccionado.frecuencia,
            }));
            return;
        }

    }, [gastoRecurrenteSeleccionado]);

    //------------------
    // BOTONES

    // * Boton para guardar
    const handleGuardarGasto = async () => {
        try {
            limpiarError();

            let formulario: FormularioGasto;
            let gasto: Gasto;

            formulario = {
                ...formGasto,
                categoriaGastoId: categoriaSeleccionada?.id,
                gastoRecurrenteId: gastoRecurrenteSeleccionado?.id ?? null,
                plantillaId: plantillaSeleccionada?.id ?? null,
                descripcion: campoInput,
            };

            // * DESDE GASTO COMUN EXISTENTE
            if (gastoSeleccionado) {

                // * editar
                const dto = mapGastoService.mapEditarGastoDTO(
                    formulario,
                    gastoSeleccionado,
                );
                gasto = await gastoServiceWrite.editar(gastoSeleccionado.id, dto);
                modificarGasto(gasto);
                return;
            }

            // * crear desde recurrente
            if(gastoRecurrenteSeleccionado) {
                const dto = mapGastoService.mapRegistrarGastoDesdeRecurrenteDTO(
                    formulario,
                );
                gasto = await gastoServiceWrite.registrarDesdeRecurrente(dto);
                registrarGasto(gasto);
                return;
            }

            // * Crear gasto comun nuevo
            if(categoriaGastoSeleccionada) {
                const dto = mapGastoService.mapRegistrarGastoDTO(
                        formulario,
                );
                gasto = await gastoServiceWrite.registrar(dto);
                registrarGasto(gasto);
                return
            }

        } catch (error) {
            if (error instanceof Error) {
                setError(error.message);
            }
        }
    };
    const handleGuardarRecurrencia = async () => {
        try {
            limpiarError();

            let formulario: FormularioGasto;
            let gastoRecurrente: GastoRecurrente;
            let gasto: Gasto;

            formulario = {
                ...formGasto,
            };

            // * DESDE GASTO COMUN EXISTENTE
            if (gastoSeleccionado && esRecurrente) {

                // * crear gasto recurrente
                if (!gastoRecurrenteSeleccionado) {
                    const dto = mapGastoService.mapVolverRecurreDTO(formulario);
                    gasto = await gastoServiceWrite.volverRecurrente(gastoSeleccionado.id, dto);
                    if(gasto.gastoRecurrenteId){
                        gastoRecurrente = await gastoRecurrenteServiceRead.obtenerPorId(gasto.gastoRecurrenteId);
                        registrarGastoRecurrente(gastoRecurrente);
                    } else {
                        throw new Error("Falta recurrencia");
                    }
                    return;
                }
            }

            // * DESDE PLANTILLA GASTO RECURRENTE
            if(gastoRecurrenteSeleccionado) {
                // * editar periodicidad
                const dto = mapGastoRecurrenteService.mapEditarPeriodicidadGastoRecurrenteDTO(
                    formulario,
                    gastoRecurrenteSeleccionado,
                );
                gastoRecurrente = await gastoRecurrenteServiceWrite.editarPeriocidad(gastoRecurrenteSeleccionado.id, dto);
                modificarGastoRecurrente(gastoRecurrente);
                return;
            }

        } catch (error) {
            if (error instanceof Error) {
                setError(error.message);
            }
        }
    };

    //------------------
    return (
        <div className="flex flex-col gap-1">
{/** Fecha */}
            <ButtonCalendario 
                fecha={
                    gastoSeleccionado 
                        ? gastoSeleccionado.fechaCreacion 
                        : selectedDate
                }
                theme={theme} 
            />
{/** Contenedor Money */}
            <MoneyCalculatorContenedor
                total={total ?? 0}
                expresion={expresion}
                theme={theme}
            />
            
{/** Contenedor categoria */}
            <div className="flex items-center justify-between py-2 gap-7">
                <div className="flex gap-2">
                    <div 
                        className="w-12 h-12 rounded-full border
                        flex justify-center items-center overflow-hidden"
                        style={{
                            borderColor: borderCategoria,
                            backgroundColor: bgCategoria,
                        }}
                    >
                        <img
                            src={iconCategoria}
                            alt={nombreCategoria}
                            className="w-full h-full p-[6px] object-contain"
                        />
                    </div>
                    <div className="flex flex-col justify-center gap-1 tracking-wide">
                        <h1 className="leading-none text-sm">CATEGORIA</h1>
                        <p 
                            className="font-beiruti text-base leading-none" 
                            style={{color: theme.colors[700]}}
                        >
                            {nombreCategoria}
                        </p>
                    </div>
                </div> 
                {categoriaGastoSeleccionada 
                    && 
                    <SelectorPlantilla
                    plantillas={plantillasPorCategoria}
                    theme={theme}
                    />
                }
{/** Contenedor Gasto Recurrente */}
                {plantillaConfig && 
                    <div className="flex items-center justify-center gap-3 px-10">
                        <div 
                            className="w-10 h-10  rounded-full
                            flex justify-center items-center overflow-hidden"
                            style={{
                                backgroundColor: bgPlantilla,
                                borderColor: borderPlantilla,
                            }}
                        >
                            <img
                                src={iconPlantilla}
                                alt={gastoRecurrenteSeleccionado?.descripcion 
                                    || plantillaSeleccionada?.descripcion || ""}
                                className="w-full h-full p-[6px] object-contain"
                            />
                        </div>
                        <div className="flex flex-col justify-center gap-1 tracking-wide">
                            <h1 className=" text-base leading-none">
                                {gastoRecurrenteSeleccionado ? "Gasto Recurrente" : "Plantilla"}
                            </h1>
                            <p className="font-beiruti leading-none">
                                {nombrePlantilla}
                            </p>
                        </div>
                    </div>
                }
            </div>

{/** Contenedor Datos */}
            {!gastoRecurrenteSeleccionado && 
                <FormGroup>
                    <FormControlLabel
                        control={
                            <Checkbox 
                                size="small"
                                checked={esRecurrente}
                                onChange={(event) => {
                                    setEsRecurrente(event.target.checked);
                                }}
                                sx={{
                                    color: theme.colors[900],
                                    "&.Mui-checked": {
                                        color: theme.colors[700],
                                    },
                                }}
                            />
                        }
                        label="Registrar como gasto fijo"
                        sx={{
                            "& .MuiFormControlLabel-label": {
                                fontFamily: "var(--font-sans)",
                                fontSize: "0.8rem",
                            },
                        }}
                    />
                </FormGroup>
            }
            {(gastoRecurrenteSeleccionado ?? esRecurrente)
                && (<div> 
                        <ResumenRecurrencia
                            onClick={() => setOpenRecurrencia(true)}
                            gasto={formGasto}
                            theme={theme}
                        />
                        <ModalRecurrencia
                            formRecurrencia={ formGasto }
                            setFormRecurrencia={setFormGasto}
                            theme={theme}
                            open={openRecurrencia}
                            onClose={() => setOpenRecurrencia(false)}
                        />
                    </div>)
            }
            <div className="flex items-center justify-center gap-3"> 
                <MenuBookIcon sx={{fontSize: 25, color: theme.colors[700]}}/>
                <div className="flex items-end">
                    <div 
                        className="pb-2 border-b"
                        style={{borderBottom: `1px solid ${theme.colors[700]}`,}}
                    >
                        <h1 className="font-beiruti text-sm">
                            NOTA:
                        </h1>
                    </div>
                    <Input 
                        campo={campoInput}
                        setForm={setCampoInput}
                        theme={theme}
                    />
                </div>
            </div>

{/** Contenedor Error */}
            {error && (
                <p className="text-center text-sm">
                    ⚠️ {error}
                </p>
            )}

{/** Contenedor Teclado */}
            <div className="">
                <Teclado 
                    onClick={presionarTecla}
                    onDelete={borrar}
                    onClear={limpiar}
                    theme={theme}
                />
            </div>

            <div className="flex justify-center">
                <button
                    onClick={esRecurrente
                        ? handleGuardarRecurrencia
                        : handleGuardarGasto
                    }
                    className="border rounded-full w-20 h-20 active:scale-80  
                    hover:bg-[var(--button-hover)] 
                    transition-all"
                    style={{
                        borderColor: theme.colors[300],
                        color: theme.colors[700],
                        "--button-hover": `${theme.colors[300]}50`,
                    } as React.CSSProperties}
                >
                    <IconOk className="w-full h-full"/>
                </button>
            </div>

        </div>
    );
};