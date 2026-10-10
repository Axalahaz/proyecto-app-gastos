import dayjs from "dayjs";
import type { FormularioGasto } from "../../dto/gasto/FormularioGasto";
import type { RegistrarGastoRequest } from "../../dto/gasto/RegistrarGastoRequest";
import type { VolverRecurrenteRequest } from "../../dto/gasto/VolverRecurrenteRequest";
import { FRECUENCIA } from "../../types/typeGastos";
import type { EditarGastoRequest } from "../../dto/gasto/EditarGastoRequest";
import type { Gasto } from "../../dto/gasto/Gasto";
import type { RegistrarGastoDesdeRecurrente } from "../../dto/gasto/RegistrarGastoDesdeRecurrente";

const mapRegistrarGastoDTO = (
    formulario: FormularioGasto
): RegistrarGastoRequest => {

    if (formulario.monto === undefined) {
        throw new Error("Falta dato de monto");
    }
    if (formulario.categoriaGastoId === undefined) {
        throw new Error("Falta la categoria del gasto");
    }

    return {
    monto: formulario.monto,
    descripcion: formulario.descripcion ?? null,
    categoriaGastoId: formulario.categoriaGastoId,
    }
};

const mapRegistrarGastoDesdeRecurrenteDTO = (
    formulario: FormularioGasto
): RegistrarGastoDesdeRecurrente => {

    if (formulario.monto === undefined) {
        throw new Error("Falta dato de monto");
    }
    if (formulario.gastoRecurrenteId === undefined 
        || formulario.gastoRecurrenteId === null) {
        throw new Error("Faltan datos de recurrencia");
    }
    if (formulario.diaVencimiento === undefined) {
        throw new Error("Falta el día de vencimiento");
    }

    let vencimiento = dayjs()
        .date(formulario.diaVencimiento)
        .hour(0)
        .minute(0)
        .second(0)
        .millisecond(0);

    if (formulario.mesVencimiento !== undefined) {
        vencimiento = vencimiento.month(formulario.mesVencimiento - 1);
    }

    return {
        monto: formulario.monto,
        gastoRecurrenteId: formulario.gastoRecurrenteId,
        fechaVencimiento: vencimiento,
    };
};

const mapVolverRecurreDTO = (
    formulario: FormularioGasto
): VolverRecurrenteRequest => {

    if (formulario.frecuencia === undefined) {
        throw new Error("Falta frecuencia");
    }
    if (formulario.diaVencimiento === undefined) {
        throw new Error("Falta el día de vencimiento");
    }
    if (formulario.frecuencia === FRECUENCIA.ANUAL 
        && formulario.mesVencimiento === undefined) {
        throw new Error("Falta el mes de vencimiento");
    }

    return {
        diaVencimiento: formulario.diaVencimiento,
        mesVencimiento: formulario.mesVencimiento,
        frecuencia: formulario.frecuencia,
    };
};

const mapEditarGastoDTO = (
    formulario: FormularioGasto,
    gastoSeleccionado: Gasto,
): EditarGastoRequest => {

    const dto: EditarGastoRequest = {};

    if (gastoSeleccionado.monto !== formulario.monto) {
        dto.monto = formulario.monto;
    }

    if (gastoSeleccionado.descripcion !== formulario.descripcion) {
        dto.descripcion = formulario.descripcion;
    }

    if (gastoSeleccionado.categoriaGastoId !== formulario.categoriaGastoId) {
        dto.categoriaGastoId = formulario.categoriaGastoId;
    }

    return dto;
};

export default {
    mapEditarGastoDTO,
    mapVolverRecurreDTO,
    mapRegistrarGastoDesdeRecurrenteDTO,
    mapRegistrarGastoDTO
}

