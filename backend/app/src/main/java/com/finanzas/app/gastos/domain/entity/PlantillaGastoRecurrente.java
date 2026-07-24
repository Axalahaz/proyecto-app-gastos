package com.finanzas.app.gastos.domain.entity;

import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

/*
 * Es la plantilla de la configuracion basica del sistema para gasto recurrente
 * */
@Getter
public class PlantillaGastoRecurrente {

    private Long id;
    private String descripcion;
    private Fecha fechaCreacion;

    // ----------------------------------
    // CONSTRUCTOR
    
    private PlantillaGastoRecurrente(
    		Long id,
    		String descripcion,
    		Fecha fechaCreacion
    ) {
    	validarDescripcion(descripcion);
    	validarFechaCreacion(fechaCreacion);

        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static PlantillaGastoRecurrente crear(
    		String descripcion,
    		Fecha fechaCreacion
    ) {
        return new PlantillaGastoRecurrente(
        		null,
        		descripcion,
        		fechaCreacion
        );
    }
    
    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static PlantillaGastoRecurrente reconstruir(
    		Long id,
    		String descripcion,
    		Fecha fechaCreacion
    ) {
        return new PlantillaGastoRecurrente(
        		id,
        		descripcion,
        		fechaCreacion
        );
    }
    
    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // EDICION
    
    public void editar(String descripcion) {

        if (descripcion == null) {
            return;
        }

        if (this.descripcion.equals(descripcion)) {
            return;
        }

        validarDescripcion(descripcion);

        this.descripcion = descripcion;
    }
    
    // ----------------------------------------------------
    // VALIDACION
    // ----------------------------------------------------
    
    // VALIDACION DE DATOS
    
    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw ValidationException.of(
                "La descripción es obligatoria" );
        }
    }
    
    private void validarFechaCreacion(Fecha fechaCreacion) {
        if (fechaCreacion == null) {
            throw ValidationException.of(
                "La fecha de creacion es obligatoria");
        }
    }
}