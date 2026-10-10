import { useState } from "react";

import Dialog from "@mui/material/Dialog";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";

import type { ThemeProps } from "@/shared/theme/themes";
import { useCalculadora } from "@/shared/hooks/useCalculadora";
import { useGastos } from "@/modules/gastos/context/useGastos";
import { useFilter } from "@/hooks/useFilter";
import { useDatosCalculadora } from "@/modules/gastos/hooks/useDatosCalculadora";
import { useModoRecurrente } from "@/modules/gastos/hooks/useModoRecurrente";
import { useHandleAddGastos } from "@/modules/gastos/hooks/useHandleAddGastos";

import { ModalRecurrencia } from "@/modules/gastos/components/ModuleCalculadora/recurrencia/ModalRecurrencia";
import { ResumenRecurrencia } from "@/modules/gastos/components/ModuleCalculadora/recurrencia/ResumenRecurrencia";

import NoteAltOutlinedIcon from '@mui/icons-material/NoteAltOutlined';

import { Input } from "@/shared/components/calculadora/Input";

import { MoneyCalculatorContenedor } from "@/shared/components/calculadora/MoneyCalculatorContenedor";
import { ButtonCalendario } from "@/shared/components/calculadora/ButtonCalendario";
import { GastoInfo } from "@/modules/gastos/components/ModuleCalculadora/GastoInfo";
import { Teclado } from "@/shared/components/calculadora/Teclado";
import { CheckboxBoton } from "@/shared/components/CheckboxBoton";


interface Props extends ThemeProps {
    open: boolean;
    onClose: () => void;
}

export const ModalCalculadora = ({
    open,
    onClose,
    theme,
}: Props) => {

    const { selectedDate } = useFilter();

    // -----------------------------
    // CALCULADORA
    const {
        total,
        expresion,
        borrar,
        limpiar,
        presionarTecla
    } = useCalculadora();

    // -----------------------------
    // CONTEXTO
    const { 
        gastoSeleccionado,
        modificarGasto,
        registrarGasto,

        categoriaGastoSeleccionada, 
        categoriasGastos,

        gastosRecurrentes,
        gastoRecurrenteSeleccionado,
        modificarGastoRecurrente,
        registrarGastoRecurrente,

        plantillasGastos,
        plantillaGastoSeleccionada,
        seleccionarPlantillaGasto,
    } = useGastos();

    // -----------------------------
    // DATOS
    const {
        categoriaSeleccionada,
        plantillaSeleccionada,
        nombreCategoria,
        nombrePlantilla,
        plantillasPorCategoria,

        formGasto,
        setFormGasto,

    } = useDatosCalculadora({
        gastoSeleccionado,
        gastoRecurrenteSeleccionado,
        categoriaGastoSeleccionada,
        total,
        categoriasGastos,
        plantillasGastos,
        plantillaGastoSeleccionada,
    });

    //------------------
    // MODAL RECURRENCIA
    const [openRecurrencia, setOpenRecurrencia] = useState(false);

    const {
        esRecurrente,
        setEsRecurrente,
        
    } = useModoRecurrente({
        formGasto,
        setFormGasto,
        gastosRecurrentes,
        gastoRecurrenteSeleccionado,
    });

    //------------------
    // TECLADO
    const [activeTeclado, setActiveTeclado] = useState(false);

    //------------------
    // BOTONES
    const {
        handleGuardarGasto,
        handleGuardarRecurrencia,
        error

    } = useHandleAddGastos({
        formGasto,
        gastoSeleccionado,
        categoriaGastoSeleccionada, 
        gastoRecurrenteSeleccionado,
        plantillaSeleccionada,
        modificarGasto,
        registrarGasto,
        modificarGastoRecurrente,
        registrarGastoRecurrente,
        esRecurrente,
    });

    //------------------
    return (
        <Dialog 
            open={open}
            onClose={onClose}
        >
            <DialogTitle 
                className="text-center" 
                style={{
                    paddingBottom:0,
                    fontFamily:"var(--font-beiruti)",
                    fontSize: "1.2rem",
                    color: theme.colors[900], 
                    background: theme.colors[100], 
                    boxShadow: `0 0 10px ${theme.colors[100]}`, 
                }}
            >
                {gastoRecurrenteSeleccionado && "REGISTRAR - GASTO RECURRENTE -"}
                {gastoSeleccionado && "MODIFICAR - GASTO -"}
                {categoriaGastoSeleccionada && "REGISTRAR - NUEVO GASTO -"}
            </DialogTitle>
            <DialogContent 
            className="flex flex-col"
            style={{paddingTop:15,}}
            >
                <div className="flex flex-col gap-3">
                    <ButtonCalendario
                        fecha={
                            gastoSeleccionado 
                                ? gastoSeleccionado.fechaCreacion 
                                : selectedDate
                        }
                        theme={theme} 
                    />
                    <MoneyCalculatorContenedor
                        total={total ?? 0}
                        expresion={expresion}
                        theme={theme}
                    />
                    <div className="flex justify-center">
                        <GastoInfo
                            categoria={categoriaSeleccionada}
                            nombreCategoria={nombreCategoria}
                            recurrenteSeleccionado={gastoRecurrenteSeleccionado}
                            nombrePlantilla={nombrePlantilla}
                            plantilla={plantillaSeleccionada}
                            plantillas={plantillasPorCategoria}
                            plantillaSeleccionadaSelector={plantillaGastoSeleccionada}
                            plantillaSelector={seleccionarPlantillaGasto}
                            mostrarSelector={!!gastoSeleccionado}
                            theme={theme}
                        />
                    </div>
                    <div className="flex items-center gap-2"> 
                        <NoteAltOutlinedIcon sx={{fontSize: 30, color: theme.colors[900]}}/>
                        <div 
                            className="flex items-center w-full"
                            style={{
                                height: 20,
                                borderBottom: `1px solid ${theme.colors[700]}`,
                            }}
                        >
                            <span className="font-beiruti text-base whitespace-nowrap">
                                NOTA:
                            </span>
                            <Input 
                                campo={formGasto.descripcion ?? ""}
                                setForm={setFormGasto}
                                theme={theme}
                            />
                        </div>
                    </div>
        {/** Contenedor Datos */}
                    {(!gastoRecurrenteSeleccionado && !esRecurrente) && (
                            <CheckboxBoton
                                texto = "Registrar como gasto fijo"
                                fontSize = "0.8rem"
                                esActivo = {esRecurrente}
                                setEsActivo = {setEsRecurrente}
                                sizeCheckbox = "small"
                                theme = {theme}
                            />
                    )}
                    {(gastoRecurrenteSeleccionado ?? esRecurrente) && (
                        <div> 
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
                        </div>
                    )}
                    
        {/** Contenedor Error */}
                    {error && (
                        <p className="text-center text-sm">
                            ⚠️ {error}
                        </p>
                    )}
        {/** Contenedor Teclado */}
                    <div className="flex flex-col gap-2">
                        <CheckboxBoton
                            texto = "Activar Teclado Virtual"
                            fontSize = "0.8rem"
                            esActivo = {activeTeclado}
                            setEsActivo = {setActiveTeclado}
                            sizeCheckbox = "small"
                            theme = {theme}
                        />
                        {activeTeclado && (
                            <Teclado 
                                onClick={presionarTecla}
                                onDelete={borrar}
                                onClear={limpiar}
                                theme={theme}
                            />
                        )}
                    </div>
                </div>
            </DialogContent>
            <DialogActions>
                <Button
                    onClick={esRecurrente
                        ? handleGuardarRecurrencia
                        : handleGuardarGasto
                    }
                    sx={{
                        backgroundColor: theme.colors[900],
                        color: theme.colors[100],
                        fontFamily: "var(--font-beiruti)",
                        fontSize: "1rem",
                        "&:hover": { 
                            backgroundColor: theme.colors[300],
                            color: theme.colors[900],
                        },
                    }}
                >
                    Guardar
                </Button>
                <Button
                    onClick={onClose}
                    sx={{
                        backgroundColor: theme.colors[900],
                        color: theme.colors[100],
                        fontFamily: "var(--font-beiruti)",
                        fontSize: "1rem",
                        "&:hover": { 
                            backgroundColor: theme.colors[300],
                            color: theme.colors[900],
                        },
                    }}
                >
                    Cerrar
                </Button>
            </DialogActions>
        </Dialog>
    );
}