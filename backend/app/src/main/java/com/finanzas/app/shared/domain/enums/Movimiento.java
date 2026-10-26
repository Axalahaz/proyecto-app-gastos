package com.finanzas.app.shared.domain.model;

import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

import com.finanzas.app.shared.domain.vo.Fecha;

@Getter
public abstract class Movimiento {

    protected Money monto;
    protected String descripcion; // puede estar vacio
    protected EstadoMovimiento estado;
    protected Fecha fechaCreacion;
    protected Fecha fechaCambioEstado;

    // -----------------------------------------
    // CONSTRUCTOR
    
    protected Movimiento (
    		Money monto, 
    		String descripcion, 
    		EstadoMovimiento estado,
    		Fecha fechaCreacion,
    		Fecha fechaCambioEstado
    		) {
    	validarMonto(monto);
    	validarDescripcion(descripcion);
    	validarFechaCreacion(fechaCreacion);

    	this.monto = monto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaCambioEstado = fechaCambioEstado;
    }

    // -----------------------------------------
    // COMPORTAMIENTO
    
    protected void actualizarMonto(Money monto) {
    	validarMonto(monto);
    	this.monto = monto;
    }
    
    protected void actualizarDescripcion(String descripcion) {
    	validarDescripcion(descripcion);
        this.descripcion = descripcion;
    }
    
    protected void cambiarEstado(EstadoMovimiento nuevoEstado, Fecha fechaCambioEstado) {
    	validarFechaCambioEstado(fechaCambioEstado);
    	validarEstado(nuevoEstado);
        this.estado = nuevoEstado;
        this.fechaCambioEstado = fechaCambioEstado;
    }

    // ----------------------------------------------------
    // VALIDACION
    
    private void validarEstado(EstadoMovimiento estado) {
    	if (estado == null) {
    		throw ValidationException.of("El estado de movimiento es obligatorio");
    	}
    }

    private void validarMonto(Money monto) {
        if (monto == null) {
            throw ValidationException.of("El monto es obligatorio");
        }
    }
    
    private void validarDescripcion(String descripcion) {
        if (descripcion == null) {
            throw ValidationException.of("La descripcion es obligatoria");
        }
    }
    
    private void validarFechaCreacion(Fecha fecha) {
        if (fecha == null) {
            throw ValidationException.of(
                "La fecha de creacion es obligatoria");
        }
    }
    
    private void validarFechaCambioEstado(Fecha fecha) {
        if (fecha == null) {
            throw ValidationException.of(
                "La fecha de cambio de estado es obligatoria");
        }
    }

}