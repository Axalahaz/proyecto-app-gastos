package com.finanzas.app.gastos.domain.entity;

import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

/*
 * Es la plantilla de la configuracion basica del sistema para gasto recurrente y gasto comun
 * */
@Getter
public class PlantillaGasto {

    private Long id;
    private Long categoriaGastoId;
    private String descripcion;
    private TipoObjeto tipo;
    private Fecha fechaCreacion;

    // ----------------------------------
    // CONSTRUCTOR
    
    private PlantillaGasto(
    		Long id,
    		Long categoriaGastoId,
    		String descripcion,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    ) {
    	validarDescripcion(descripcion);
    	validarFechaCreacion(fechaCreacion);

        this.id = id;
        this.categoriaGastoId = categoriaGastoId;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
    }
    
    // ----------------------------------------------------
    // CREAR
    
    public static PlantillaGasto crear(
    		Long categoriaGastoId,
    		String descripcion,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    ) {
        return new PlantillaGasto(
        		null,
        		categoriaGastoId,
        		descripcion,
        		tipo, 
        		fechaCreacion
        );
    }
    
    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static PlantillaGasto reconstruir(
    		Long id,
    		Long categoriaGastoId,
    		String descripcion,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    ) {
        return new PlantillaGasto(
        		id,
        		categoriaGastoId,
        		descripcion,
        		tipo, 
        		fechaCreacion
        );
    }
    
    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // EDICION
    
    public void editar(
    		Long categoriaGastoId, 
    		String nuevaDescripcion
    ) {

    	if(categoriaGastoId != null) actualizarCategoria(categoriaGastoId);
    	if(nuevaDescripcion != null) actualizarDescripcion(nuevaDescripcion);
    }
    
    private void actualizarCategoria(Long categoriaGastoId) {
    	if (this.categoriaGastoId.equals(categoriaGastoId)) {
            return;
        }
    	validarCategoria(categoriaGastoId);
        this.categoriaGastoId = categoriaGastoId;
    }
    
    private void actualizarDescripcion(String descripcion) {
    	validarDescripcion(descripcion);
        this.descripcion = descripcion;
    }
    
    // ----------------------------------------------------
    // VALIDACION
    // ----------------------------------------------------
    
    // VALIDACION DE DATOS
    
    public boolean esTipo(TipoObjeto tipo) {
        return this.tipo == tipo;
    }
    
    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw ValidationException.of(
                "La descripción es obligatoria" );
        }
        
        if (descripcion.length() > 50) {
            throw ValidationException.of("La descripcion es demasiado larga");
        }
    }
    
    private void validarFechaCreacion(Fecha fechaCreacion) {
        if (fechaCreacion == null) {
            throw ValidationException.of(
                "La fecha de creacion es obligatoria");
        }
    }
    
    private void validarCategoria(Long categoriaGastoId) {
        if (categoriaGastoId == null) {
            throw ValidationException.of("El gasto debe pertenecer a una categoría");
        }
    }
}