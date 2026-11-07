package com.finanzas.app.gastos.recurrentes.domain;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.enums.Frecuencia;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.UsuarioId;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.Getter;

/*
 * Configuracion utilizada como plantilla para registrar gastos recurrentes.
 * */
@Getter
public class ConfGastoRecurrente {

    private Long id;
    private UsuarioId usuarioId;
    private Descripcion descripcion;
    private CategoriaId categoriaGastoId;
    
    private PeriodicidadVO periodicidad;
    
    private EstadoConf estado;
    private Fecha fechaCreacion;
    private Fecha fechaModificacion;

    // ----------------------------------
    // CONSTRUCTOR
    
    private ConfGastoRecurrente(
    		Long id,
    		UsuarioId usuarioId,
    		Descripcion descripcion,
    		CategoriaId categoriaGastoId,
    		PeriodicidadVO periodicidad,
    		EstadoConf estado,
            Fecha fechaCreacion,
            Fecha fechaModificacion
    ) {
    	
        this.id = id;
        this.usuarioId = usuarioId;
        this.descripcion = descripcion;
        this.categoriaGastoId = categoriaGastoId;
        this.periodicidad = periodicidad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static ConfGastoRecurrente crear(
    		UsuarioId usuarioId,
    		Descripcion descripcion,
    		CategoriaId categoriaGastoId,
            Frecuencia frecuencia,
            Integer diaVencimiento,
            Integer mesVencimiento,
            Fecha fechaCreacion
    ) {
    	PeriodicidadVO periodicidad =  new PeriodicidadVO(
				frecuencia,
				diaVencimiento,
				mesVencimiento
				);
    	
        return new ConfGastoRecurrente(
        		null,
        		usuarioId,
        		descripcion,
        		categoriaGastoId,
        		periodicidad,
        		EstadoConf.ACTIVA,
                fechaCreacion,
                null
        );
    }

    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static ConfGastoRecurrente reconstruir(
    		Long id,
    		UsuarioId usuarioId,
    		Descripcion descripcion,
    		CategoriaId categoriaGastoId,
    		PeriodicidadVO periodicidad,
    		EstadoConf estado,
            Fecha fechaCreacion,
            Fecha fechaModificacion
    ) {
        return new ConfGastoRecurrente(
        		id,
        		usuarioId,
        		descripcion,
        		categoriaGastoId,
        		periodicidad,
        		estado,
                fechaCreacion,
                fechaModificacion
        );
    }
    
    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // ESTADO RECURRENCIA
    
    public void desactivar(Fecha fechaModificacion) {
    	validarConfRecurrenciaEstaActiva();
        this.estado = EstadoConf.INACTIVA;
        this.fechaModificacion = fechaModificacion;
    }

    public void activar(Fecha fechaModificacion) {
    	validarConfRecurrenciaEstaInactiva();
        this.estado = EstadoConf.ACTIVA;
        this.fechaModificacion = fechaModificacion;
    }
    
    public boolean estaActiva() {
        return this.estado.equals(EstadoConf.ACTIVA);
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

    public void editarDescripcion(Descripcion nuevaDescripcion) {

    	validarConfRecurrenciaEstaActiva();
    	
    	if (nuevaDescripcion != null) {
    		if (this.descripcion.equals(nuevaDescripcion)) {
    			return;
    		}
    		
    		this.descripcion = nuevaDescripcion;
    	}
    }

    // ----------------------------------------------------
    // ACTUALIZAR ATRIBUTOS PERIODICIDAD

    private PeriodicidadVO calcularNuevaPeriodicidad(
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

        return new PeriodicidadVO(frecuenciaFinal, diaFinal, mesFinal);
    }

    // ----------------------------------------------------
    // VALIDACION
    // ----------------------------------------------------
    
    // VALIDACION DE ESTADO
    
    private void validarConfRecurrenciaEstaInactiva() {
    	if (this.estado.equals(EstadoConf.ACTIVA))  {
            throw new ConflictException(
            		"No se puede operar sobre una recurrencia activa");
        }
    }
    
    public void validarConfRecurrenciaEstaActiva() {
    	if (this.estado.equals(EstadoConf.INACTIVA))  {
            throw new ConflictException(
            		"No se puede operar sobre una recurrencia inactiva");
        }
    }
    
    // VALIDACION DE ELIMINACION EN EL DIA Y DENTRO DE UNA HORA    

    public void validarEliminacion(LocalDateTime tiempoActual) {
    	validarConfRecurrenciaEstaActiva();
    	LocalDateTime fechaCreacion = this.fechaCreacion.value();

    	if (tiempoActual.isBefore(fechaCreacion)
                || tiempoActual.isAfter(fechaCreacion.plusHours(1))) {
            throw new ConflictException(
                    "Solo se pueden eliminar Conf Recurrentes dentro de la primera hora desde su creación"
            );
        }
    }
    
    //****************
}