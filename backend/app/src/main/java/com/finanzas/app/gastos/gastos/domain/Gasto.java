package com.finanzas.app.gastos.gastos.domain;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.enums.EstadoMovimiento;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.domain.vo.UsuarioId;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.Getter;

/*
 * Es el gasto real
 * */
@Getter
public class Gasto {

    private Long id;
    private UsuarioId usuarioId;
    private Money importe;
    private Descripcion descripcion;
    private Fecha fechaCreacion;
    private Fecha fechaModificacion;
    private Fecha fechaPagoRecurrente;
    private EstadoMovimiento estado;
    private CategoriaId categoriaGastoId;
    private Long confGastoRecurrenteId;

    // ----------------------------------
    // CONSTRUCTOR
    
    private Gasto(
    	    Long id,
    	    UsuarioId  usuarioId,
    	    Money importe, 
    	    Descripcion descripcion, 
    	    Fecha fechaCreacion,
    	    Fecha fechaModificacion,
    	    Fecha fechaPagoRecurrente,
    	    EstadoMovimiento estado,
    	    CategoriaId categoriaGastoId,
    	    Long confGastoRecurrenteId
    	) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.importe = importe;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaPagoRecurrente = fechaPagoRecurrente;
        this.estado = estado;
        this.categoriaGastoId = categoriaGastoId;
        this.confGastoRecurrenteId = confGastoRecurrenteId;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static Gasto crear(
    		UsuarioId usuarioId,
    		Money importe, 
    		Descripcion descripcion,
    	    Fecha fechaCreacion,
    	    Fecha fechaPagoRecurrente,
    	    CategoriaId categoriaGastoId,
    	    Long confGastoRecurrenteId
    	) {

    	return new Gasto(
    			null, 
    			usuarioId,
    			importe, 
    			descripcion, 
    			fechaCreacion,
    			null,
    			fechaPagoRecurrente,
    			EstadoMovimiento.ACTIVO,
    			categoriaGastoId, 
    			confGastoRecurrenteId
    	);
    }
    
    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static Gasto reconstruir(
            Long id,
            UsuarioId usuarioId,
            Money importe,
            Descripcion descripcion,
            Fecha fechaCreacion,
            Fecha fechaModificacion,
            Fecha fechaPagoRecurrente,
            EstadoMovimiento estado,
            CategoriaId categoriaGastoId,
            Long confGastoRecurrenteId
    	) {
    	
        return new Gasto(
                id,
                usuarioId,
                importe,
                descripcion,
                fechaCreacion,
                fechaModificacion,
                fechaPagoRecurrente,
                estado,
                categoriaGastoId,
                confGastoRecurrenteId
        );
    }
    
    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // ESTADO (no se puede activar)
    
    public void anular(Fecha fechaModificacion) {
    	validarEstadoActivo();
        this.estado = EstadoMovimiento.ANULADO;
        this.fechaModificacion = fechaModificacion;
    }

    // ----------------------------------------------------
    // EDITAR GASTO

    public void editar(
    		CategoriaId categoriaGastoId, 
    		Money nuevoImporte, 
    		Descripcion nuevaDescripcion
    	) {
    	
    	validarEstadoActivo();
    	
    	if(categoriaGastoId != null) actualizarCategoria(categoriaGastoId);
    	if(nuevoImporte != null) actualizarMonto(nuevoImporte);
    	if(nuevaDescripcion != null) actualizarDescripcion(nuevaDescripcion);
    }
    
    //*
    private void actualizarCategoria(CategoriaId categoriaGastoId) {
    	if (this.categoriaGastoId.equals(categoriaGastoId)) {
            return;
        }
        this.categoriaGastoId = categoriaGastoId;
    }
    
    protected void actualizarMonto(Money importe) {
    	this.importe = importe;
    }
    
    protected void actualizarDescripcion(Descripcion descripcion) {
        this.descripcion = descripcion;
    }
    
    // ----------------------------------------------------
    // RECURRENTE
    
    public void asociarAGastoRecurrente(Long nuevoConfGastoRecurrenteId) {
    	validarNoAsociadoConfGastoRecurrente();
    	this.confGastoRecurrenteId = nuevoConfGastoRecurrenteId;
    }
    
    public boolean esRecurrente() {
        return confGastoRecurrenteId != null;
    }
    
    // ----------------------------------------------------
    // VALIDACION
    // ----------------------------------------------------
    
    // VALIDACION DE ESTADO
    
    public void validarEstadoActivo() {
    	if (this.estado == EstadoMovimiento.ANULADO) {
            throw new ConflictException("No se puede operar sobre un gasto anulado");
        }
    }
    
    // VALIDACION ASOCIACION CONF RECURRENTE
    
    private void validarNoAsociadoConfGastoRecurrente() {
    	if (this.confGastoRecurrenteId != null) {
    		throw new ConflictException(
    				"El gasto ya esta asociado a una Configuracion Recurrente"
    				); 
    	}
    }

    //****************
    //!!!!!!!! VALIDACION DE EN EL DIA Y DENTRO DE UNA HORA    
    
    public void validarEliminacion(LocalDateTime tiempoActual) {
    	validarEstadoActivo();
    	LocalDateTime fechaCreacion = this.fechaCreacion.value();
    	
    	if (tiempoActual.isBefore(fechaCreacion)
                || tiempoActual.isAfter(fechaCreacion.plusHours(1))) {
            throw new ConflictException(
                    "Solo se pueden eliminar gastos dentro de la primera hora desde su creación"
            );
        }
    }
    
    //****************
}