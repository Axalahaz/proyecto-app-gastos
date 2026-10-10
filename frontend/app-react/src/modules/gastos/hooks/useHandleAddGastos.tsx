import { useState } from "react";
import type { CategoriaGasto } from "../dto/categoria/CategoriaGasto";
import type { FormularioGasto } from "../dto/gasto/FormularioGasto";
import type { Gasto } from "../dto/gasto/Gasto";
import type { GastoRecurrente } from "../dto/gastoRecurrente/GastoRecurrente";
import type { PlantillaGasto } from "../dto/plantillaGasto/PlantillaGasto";
import { gastoServiceWrite } from "../services/gasto/gastoServiceWrite";
import { gastoRecurrenteServiceRead } from "../services/gastoRecurrente/gastoRecurrenteServiceRead";
import { gastoRecurrenteServiceWrite } from "../services/gastoRecurrente/gastoRecurrenteServiceWrite";
import mapGastoRecurrenteService from "../services/mappers/mapGastoRecurrenteService";
import mapGastoService from "../services/mappers/mapGastoService";

type Props = {
    formGasto: FormularioGasto;

    gastoSeleccionado: Gasto | null;
    categoriaGastoSeleccionada: CategoriaGasto | null;
    gastoRecurrenteSeleccionado: GastoRecurrente | null;
    plantillaSeleccionada: PlantillaGasto | null;

    modificarGasto: (gasto: Gasto) => void;
    registrarGasto: (gasto: Gasto) => void;
    modificarGastoRecurrente: (gastoRecurrente: GastoRecurrente) => void;
    registrarGastoRecurrente: (gastoRecurrente: GastoRecurrente) => void;

    esRecurrente: Boolean;
}

export const useHandleAddGastos = ({
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
}: Props) => {

    const [error, setError] = useState<string | null>(null);

    const limpiarError = () => {
        setError(null);
    };

    // * Boton para guardar
    const handleGuardarGasto = async () => {
        try {
            limpiarError();

            let formulario: FormularioGasto;
            let gasto: Gasto;

            formulario = {
                ...formGasto,
                categoriaGastoId: categoriaGastoSeleccionada?.id,
                gastoRecurrenteId: gastoRecurrenteSeleccionado?.id ?? null,
                plantillaId: plantillaSeleccionada?.id ?? null,
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

    // -------------
    return {
        handleGuardarGasto,
        handleGuardarRecurrencia,
        error,
    };
}