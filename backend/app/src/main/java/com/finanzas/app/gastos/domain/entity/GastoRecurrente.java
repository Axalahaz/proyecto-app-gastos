package com.finanzas.app.gastos.domain.entity;

import java.time.LocalDate;

import com.finanzas.app.gastos.domain.vo.Periodicidad;
import com.finanzas.app.shared.domain.model.Frecuencia;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

/*
 * Puede usar la plantilla del sistema para crear una plantilla propia del usuario
 * */
@Getter
public class GastoRecurrente {

    private Long id;
    private Long plantillaId; // puede ser null
    private String descripcion;
    
    private Periodicidad periodicidad;
    
    private boolean activo;
    private Fecha fechaCreacion;
    private Fecha fechaCambioActivo;

    // ----------------------------------
    // CONSTRUCTOR
    
    private GastoRecurrente(
    		Long id,
    		Long plantillaId,
    		String descripcion,
    		Periodicidad periodicidad,
    		boolean activo,
            Fecha fechaCreacion,
            Fecha fechaCambioActivo
    ) {
    	validarDescripcion(descripcion);
        validarFechaCreacion(fechaCreacion);

        this.id = id;
        this.plantillaId = plantillaId;
        this.descripcion = descripcion;
        this.periodicidad = periodicidad;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaCambioActivo = fechaCambioActivo;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static GastoRecurrente crear(
    		Long plantillaId,
    		String descripcion,
    		Periodicidad periodicidad,
            Fecha fechaCreacion
    ) {
        return new GastoRecurrente(
        		null,
        		plantillaId,
        		descripcion,
        		periodicidad,
                true,
                fechaCreacion,
                null
        );
    }

    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static GastoRecurrente reconstruir(
    		Long id,
    		Long plantillaId,
    		String descripcion,
    		Periodicidad periodicidad,
            boolean activo,
            Fecha fechaCreacion,
            Fecha fechaCambioActivo
    ) {
        return new GastoRecurrente(
        		id,
        		plantillaId,
        		descripcion,
        		periodicidad,
                activo,
                fechaCreacion,
                fechaCambioActivo
        );
    }
    
    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // ESTADO RECURRENCIA
    
    public void desactivar(Fecha fecha) {
    	validarRecurrenciaEstaActiva();
        this.activo = false;
        this.fechaCambioActivo = fecha;
    }

    public void activar(Fecha fecha) {
    	validarRecurrenciaEstaInactiva();
        this.activo = true;
        this.fechaCambioActivo = fecha;
    }
    
    public boolean estaActiva() {
        return this.activo;
    }
    
    // ----------------------------------------------------
    // MODIFICAR PERIOCIDAD
    
    public void modificarPeriodicidad(
    		Frecuencia nuevaFrecuencia,
    		Integer nuevoDiaVencimiento,
            Integer nuevoMesVencimiento
        ) {
    	this.periodicidad = calcularNuevaPeriodicidad(
    			nuevaFrecuencia,
    			nuevoDiaVencimiento,
    			nuevoMesVencimiento
    			);
    }
    
    // ----------------------------------------------------
    // EDITAR

    public void editarBasico(String descripcion) {

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
    // ACTUALIZAR ATRIBUTOS PERIODICIDAD

    private Periodicidad calcularNuevaPeriodicidad(
            Frecuencia nuevaFrecuencia,
            Integer nuevoDia,
            Integer nuevoMes
    ) {

        Frecuencia frecuenciaFinal =
                nuevaFrecuencia != null
                        ? nuevaFrecuencia
                        : this.periodicidad.getFrecuencia();

        Integer diaFinal =
                nuevoDia != null
                        ? nuevoDia
                        : this.periodicidad.getDiaVencimiento();

        Integer mesFinal =
                frecuenciaFinal == Frecuencia.ANUAL
                        ? (nuevoMes != null
                            ? nuevoMes
                            : this.periodicidad.getMesVencimiento())
                        : null;

        return new Periodicidad(frecuenciaFinal, diaFinal, mesFinal);
    }

    // ----------------------------------------------------
    // VALIDACION
    // ----------------------------------------------------

    
    // VALIDACION DE ESTADO RECURRENCIA
    
    private void validarRecurrenciaEstaInactiva() {
    	if (this.activo)  {
            throw new ConflictException(
            		"No se puede operar sobre una recurrencia activa");
        }
    }
    
    public void validarRecurrenciaEstaActiva() {
    	if (!this.activo)  {
            throw new ConflictException(
            		"No se puede operar sobre una recurrencia inactiva");
        }
    }
    
    // VALIDACION DE ELIMINACION
    
    public void validarEliminacion(LocalDate tiempo) {
    	validarRecurrenciaEstaActiva();
    	if (!this.fechaCreacion.getValue().toLocalDate().equals(tiempo)) {
    		throw new ConflictException(
    				"Solo se pueden eliminar Gastos Recurrentes creados hoy"
    				);
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