package com.finanzas.app.gastos.domain.entity;

import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

/*
 * Es la plantilla de la configuracion basica del sistema para gasto recurrente
 * */
@Getter
public class PlantillaGastoRecurrente {

    private Long id;
    private String descripcion;
    private boolean activo;
    private Fecha fechaCreacion;

    // ----------------------------------
    // CONSTRUCTOR
    
    private PlantillaGastoRecurrente(
    		Long id,
    		String descripcion,
    		boolean activo,
    		Fecha fechaCreacion
    ) {
    	validarDescripcion(descripcion);
    	validarFechaCreacion(fechaCreacion);

        this.id = id;
        this.descripcion = descripcion;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static PlantillaGastoRecurrente crear(
    		String descripcion,
    		boolean activo,
    		Fecha fechaCreacion
    ) {
        return new PlantillaGastoRecurrente(
        		null,
        		descripcion,
        		activo,
        		fechaCreacion
        );
    }
    
    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static PlantillaGastoRecurrente reconstruir(
    		Long id,
    		String descripcion,
    		boolean activo,
    		Fecha fechaCreacion
    ) {
        return new PlantillaGastoRecurrente(
        		id,
        		descripcion,
        		activo,
        		fechaCreacion
        );
    }
    
    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // ESTADO
    
    public void desactivar() {
    	validarPlantillaEstaActiva();
        this.activo = false;
    }

    public void activar() {
    	validarPlantillaEstaInactiva();
        this.activo = true;
    }
    
    public boolean estaActiva() {
        return this.activo;
    }
    
    // ----------------------------------------------------
    // EDICION
    
    private void actualizarDescripcion(String descripcion) {
    	if (this.descripcion.equals(descripcion)) {
            return;
        }
    	validarDescripcion(descripcion);
    	this.descripcion = descripcion;
    }
    
    public void editar(String descripcion) {
    	if (descripcion != null) actualizarDescripcion(descripcion);
    }

    
    // ----------------------------------------------------
    // VALIDACION
    // ----------------------------------------------------
    
    // VALIDACION DE ESTADO RECURRENCIA
    
    public void validarPlantillaEstaActiva() {
    	if (!this.activo)  {
    		throw new ConflictException(
    				"No se puede operar sobre una Plantilla inactiva");
    	}
    }

    public void validarPlantillaEstaInactiva() {
    	if (this.activo)  {
            throw new ConflictException(
            		"No se puede operar sobre una Plantilla activa");
        }
    }
    
    
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