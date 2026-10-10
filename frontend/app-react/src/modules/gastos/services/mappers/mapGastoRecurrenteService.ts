import type { FormularioGasto } from "../../dto/gasto/FormularioGasto";
import type { GastoRecurrente } from "../../dto/gastoRecurrente/GastoRecurrente";
import type { EditarPeriodicidadRequest } from "../../dto/gastoRecurrente/EditarPeriodicidadRequest";
import type { EditarGastoRecurrenteSinPeriodicidadRequest } from "../../dto/gastoRecurrente/EditarGastoRecurrenteSinPeriodicidadRequest";
import type { RegistrarGastoRecurrenteRequest } from "../../dto/gastoRecurrente/RegistrarGastoRecurrenteRequest";

const mapRegistrarGastoRecurrenteDTO = (
    formulario: FormularioGasto
): RegistrarGastoRecurrenteRequest => {

    if (formulario.categoriaGastoId === undefined) {
        throw new Error("Falta categoria del Gasto Recurrente");
    }
    if (formulario.descripcion === undefined) {
        throw new Error("Falta descripcion del Gasto Recurrente");
    }
    if (formulario.diaVencimiento === undefined) {
        throw new Error("Falta el dia de vencimiento");
    }
    if (formulario.mesVencimiento === undefined) {
        throw new Error("Falta el mes de vencimiento");
    }
    if (formulario.frecuencia === undefined) {
        throw new Error("Falta la frecuencia del Gasto Recurrente");
    }
    
    return {
    categoriaGastoId: formulario.categoriaGastoId,
    plantillaGastoRecurrenteId: formulario.plantillaId ?? undefined,
    descripcion: formulario.descripcion,
    diaVencimiento: formulario.diaVencimiento,
    mesVencimiento: formulario.mesVencimiento ?? null,
    frecuencia: formulario.frecuencia,
    }
};

const mapEditarBasicoGastoRecurrenteDTO = (
    formulario: FormularioGasto,
    gastoRecurrenteSeleccionado: GastoRecurrente,
): EditarGastoRecurrenteSinPeriodicidadRequest => {

    const dto: EditarGastoRecurrenteSinPeriodicidadRequest = {};

    if (gastoRecurrenteSeleccionado.descripcion !== formulario.descripcion) {
        dto.descripcion = formulario.descripcion;
    }
    return dto;
};

const mapEditarPeriodicidadGastoRecurrenteDTO = (
    formulario: FormularioGasto,
    gastoRecurrenteSeleccionado: GastoRecurrente,
): EditarPeriodicidadRequest => {

    const dto: EditarPeriodicidadRequest = {};

    if (gastoRecurrenteSeleccionado.diaVencimiento !== formulario.diaVencimiento) {
        dto.diaVencimiento = formulario.diaVencimiento;
    }

    if (gastoRecurrenteSeleccionado.mesVencimiento !== formulario.mesVencimiento) {
        dto.mesVencimiento = formulario.mesVencimiento;
    }

    if (gastoRecurrenteSeleccionado.frecuencia !== formulario.frecuencia) {
        dto.frecuencia = formulario.frecuencia;
    }

    return dto;
};

export default {
    mapRegistrarGastoRecurrenteDTO,
    mapEditarBasicoGastoRecurrenteDTO,
    mapEditarPeriodicidadGastoRecurrenteDTO,
}