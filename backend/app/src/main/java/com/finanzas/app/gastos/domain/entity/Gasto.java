package com.finanzas.app.gastos.domain.entity;

import com.finanzas.app.shared.domain.model.Movimiento;

import java.time.LocalDate;

import com.finanzas.app.shared.domain.model.EstadoMovimiento;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.exception.extend.ConflictException;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

/*
 * Es el gasto real
 * */
@Getter
public class Gasto extends Movimiento {

    private Long id;
    private Long categoriaGastoId;
    private Long gastoRecurrenteId;

    // ----------------------------------
    // CONSTRUCTOR
    
    private Gasto(
    	    Long id,
    	    Long categoriaGastoId,
    	    Long gastoRecurrenteId,
    	    Money monto, 
    	    String descripcion, 
    	    EstadoMovimiento estado,
    	    Fecha fechaCreacion,
    	    Fecha fechaCambioEstado
    	) {
        super(monto, descripcion, estado, fechaCreacion, fechaCambioEstado);

        validarCategoria(categoriaGastoId);
        
        this.id = id;
        this.categoriaGastoId = categoriaGastoId;
        this.gastoRecurrenteId = gastoRecurrenteId;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static Gasto crear(
    		Long categoriaGastoId,
    		Long gastoRecurrenteId,
    		Money monto, 
    		String descripcion,
    	    Fecha fechaCreacion
    	) {

    	return new Gasto(
    			null, 
    			categoriaGastoId, 
    			gastoRecurrenteId,
    			monto, 
    			descripcion, 
    			EstadoMovimiento.ACTIVO,
    			fechaCreacion,
    			null
    			);
    }
    
    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static Gasto reconstruir(
            Long id,
            Long categoriaGastoId,
            Long gastoRecurrenteId,
            Money monto,
            String descripcion,
            EstadoMovimiento estado,
            Fecha fechaCreacion,
            Fecha fechaCambioEstado
    	) {
    	
        return new Gasto(
                id,
                categoriaGastoId,
                gastoRecurrenteId,
                monto,
                descripcion,
                estado,
                fechaCreacion,
                fechaCambioEstado
        );
    }
    
    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // ESTADO
    
    public void anular(Fecha fechaCambioEstado) {
    	validarEstadoNoAnulado();
        cambiarEstado(EstadoMovimiento.ANULADO, fechaCambioEstado);
    }
    
    // ----------------------------------------------------
    // EDITAR GASTO

    public void editar(
    		Long categoriaGastoId, 
    		Money nuevoMonto, 
    		String nuevaDescripcion
    	) {
    	
    	validarEstadoNoAnulado();
    	
    	if(categoriaGastoId != null) actualizarCategoria(categoriaGastoId);
    	if(nuevoMonto != null) actualizarMonto(nuevoMonto);
    	if(nuevaDescripcion != null) actualizarDescripcion(nuevaDescripcion);
    }
    
    //*
    private void actualizarCategoria(Long categoriaGastoId) {
    	if (this.categoriaGastoId.equals(categoriaGastoId)) {
            return;
        }
    	validarCategoria(categoriaGastoId);
        this.categoriaGastoId = categoriaGastoId;
    }
    
    // ----------------------------------------------------
    // GASTO RECURRENTE
    
    public void asociarAGastoRecurrente(Long nuevoGastoRecurrenteId) {
    	validarNoAsociadoAGastoRecurrente();
    	this.gastoRecurrenteId = nuevoGastoRecurrenteId;
    }
    
    public boolean esRecurrente() {
        return gastoRecurrenteId != null;
    }
    
    // ----------------------------------------------------
    // VALIDACION
    
    public void validarEliminacion(LocalDate tiempo) {
    	validarEstadoNoAnulado();
    	if (!fechaCreacion.getValue().toLocalDate().equals(tiempo)) {
    		throw new ConflictException(
    				"Solo se pueden eliminar gastos creados hoy"
    				);
    	}
    	if (gastoRecurrenteId != null) {
    	    throw new ConflictException(
    	            "Los gastos generados desde recurrentes solo pueden anularse"
    	    );
    	}
    }
    
    public void validarEstadoNoAnulado() {
    	if (this.estado == EstadoMovimiento.ANULADO) {
            throw new ConflictException("No se puede operar sobre un gasto anulado");
        }
    }
    
    private void validarNoAsociadoAGastoRecurrente() {
    	if (this.gastoRecurrenteId != null) {
    		throw new ConflictException(
    				"El gasto ya esta asociado a un gasto recurrente"
    				); 
    	}
    }
    
    private void validarCategoria(Long categoriaGastoId) {
        if (categoriaGastoId == null) {
            throw ValidationException.of("El gasto debe pertenecer a una categoría");
        }
    }
}