package com.finanzas.app.gastos.domain.vo;

import com.finanzas.app.gastos.domain.entity.Frecuencia;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

@Getter
public class Periodicidad {

    private final Frecuencia frecuencia;
    private final Integer diaVencimiento;
    private final Integer mesVencimiento;

    public Periodicidad(
            Frecuencia frecuencia,
            Integer diaVencimiento,
            Integer mesVencimiento
    ) {
        validarFrecuencia(frecuencia);
        validarPeriodicidad(
                frecuencia,
                diaVencimiento,
                mesVencimiento
        );

        this.frecuencia = frecuencia;
        this.diaVencimiento = diaVencimiento;
        this.mesVencimiento = mesVencimiento;
    }

    public boolean esAnual() {
        return frecuencia == Frecuencia.ANUAL;
    }

    public boolean esMensual() {
        return frecuencia == Frecuencia.MENSUAL;
    }

    private void validarFrecuencia(Frecuencia frecuencia) {
        if (frecuencia == null) {
            throw ValidationException.of(
                    "La frecuencia (MENSUAL/ANUAL) es obligatoria");
        }
    }

    private void validarPeriodicidad(
            Frecuencia frecuencia,
            Integer dia,
            Integer mes
    ) {

        validarDia(dia);

        if (frecuencia == Frecuencia.ANUAL) {

            if (mes == null) {
                throw ValidationException.of(
                        "El mes es obligatorio para frecuencia anual");
            }

            validarMes(mes);

        } else {

            if (mes != null) {
                throw ValidationException.of(
                        "Solo los gastos anuales pueden tener mes");
            }
        }
    }

    private void validarDia(Integer dia) {

        if (dia == null || dia < 1 || dia > 31) {
            throw ValidationException.of(
                    "El día debe estar entre 1 y 31");
        }
    }

    private void validarMes(Integer mes) {

        if (mes < 1 || mes > 12) {
            throw ValidationException.of(
                    "El mes debe estar entre 1 y 12");
        }
    }
}