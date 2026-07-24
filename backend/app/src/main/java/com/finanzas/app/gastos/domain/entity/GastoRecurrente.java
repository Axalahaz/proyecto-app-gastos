package com.finanzas.app.gastos.domain.entity;

import com.finanzas.app.gastos.domain.vo.Periodicidad;
import com.finanzas.app.shared.domain.model.Frecuencia;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

/*
 * Usa la plantilla del sistema para crear una plantilla propia del usuario
 * */
@Getter
public class GastoRecurrente {

    private Long id;
    private String descripcion;
    
    private Periodicidad periodicidad;
    private boolean periodicidadActiva;
    
    private boolean activo;
    private Fecha fechaCreacion;
    private Fecha fechaCambioActivo;

    // ----------------------------------
    // CONSTRUCTOR
    
    private GastoRecurrente(
    		Long id,
    		String descripcion,
    		Periodicidad periodicidad,
    		boolean periodicidadActiva,
    		boolean activo,
            Fecha fechaCreacion,
            Fecha fechaCambioActivo
    ) {
        validarPeriodicidad(periodicidadActiva, periodicidad);
    	validarDescripcion(descripcion);
        validarFechaCreacion(fechaCreacion);

        this.id = id;
        this.descripcion = descripcion;
        this.periodicidad = periodicidad;
        this.periodicidadActiva = periodicidadActiva;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaCambioActivo = fechaCambioActivo;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static GastoRecurrente crear(
    		String descripcion,
    		Periodicidad periodicidad,
    		boolean periodicidadActiva,
            Fecha fechaCreacion
    ) {
        return new GastoRecurrente(
        		null,
        		descripcion,
        		periodicidad,
        		periodicidadActiva,
                true,
                fechaCreacion,
                null
        );
    }

    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static GastoRecurrente reconstruir(
    		Long id,
    		String descripcion,
    		Periodicidad periodicidad,
    		boolean periodicidadActiva,
            boolean activo,
            Fecha fechaCreacion,
            Fecha fechaCambioActivo
    ) {
        return new GastoRecurrente(
        		id,
        		descripcion,
        		periodicidad,
        		periodicidadActiva,
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
    // ESTADO PERIOCIDAD
    
    public void quitarPeriodicidad() {
    	validarPeriodicidadEstaActiva();
        this.periodicidadActiva = false;
    }

    public void agregarPeriodicidad(
    		Frecuencia nuevaFrecuencia,
    		Integer nuevoDiaVencimiento,
            Integer nuevoMesVencimiento
        ) {
    	validarPeriodicidadEstaInactiva();
    	
    	this.periodicidadActiva = true;
    	this.periodicidad = new Periodicidad(
    			nuevaFrecuencia,
    			nuevoDiaVencimiento,
    			nuevoMesVencimiento
    			);
    }
    
    public void modificarPeriodicidad(
    		Frecuencia nuevaFrecuencia,
    		Integer nuevoDiaVencimiento,
            Integer nuevoMesVencimiento
        ) {
    	validarPeriodicidadEstaActiva();
    	this.periodicidad = calcularNuevaPeriodicidad(
    			nuevaFrecuencia,
    			nuevoDiaVencimiento,
    			nuevoMesVencimiento
    			);
    }
    
    public boolean estaPeriodicidadActiva() {
        return this.periodicidadActiva && this.periodicidad != null;
    }
    
    public boolean estaPeriodicidadInactiva() {
        return !this.periodicidadActiva && this.periodicidad != null;
    }
    
    // ----------------------------------------------------
    // EDITAR

    public void editarBasico(String descripcion ) {
    	validarRecurrenciaEstaActiva();
    	
    	if (descripcion != null) actualizarDescripcion(descripcion);
    }

    // ----------------------------------------------------
    // ACTUALIZAR ATRIBUTOS

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

    private void actualizarDescripcion(String descripcion) {
    	if (this.descripcion.equals(descripcion)) {
            return;
        }
    	validarDescripcion(descripcion);
    	this.descripcion = descripcion;
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
    
    // VALIDACION DE ESTADO PERIOCIDAD
    
    private void validarPeriodicidadEstaInactiva() {
    	if (this.periodicidadActiva)  {
            throw new ConflictException(
            		"No se puede operar con periodicidad activa");
        }
    }
    
    public void validarPeriodicidadEstaActiva() {
    	if (!this.periodicidadActiva)  {
            throw new ConflictException(
            		"No se puede operar con periodicidad inactiva");
        }
    }
    
    // VALIDACION DE DATOS

    private static void validarPeriodicidad(boolean periodicidadActiva, Periodicidad periodicidad) {
    	if (periodicidadActiva && periodicidad == null) {
    		throw ValidationException.of("La periodicidad es obligatoria");
    	}
    }
    
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