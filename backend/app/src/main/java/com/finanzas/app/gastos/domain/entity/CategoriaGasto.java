package com.finanzas.app.gastos.domain.entity;

import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

@Getter
public class CategoriaGasto {

    private Long id;
    private String nombre;
    private TipoObjeto tipo;
    private Fecha fechaCreacion;
    
    // ----------------------------------
    // CONSTRUCTOR

    private CategoriaGasto(
    		Long id,
    		String nombre,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    		) {
        
        validarNombre(nombre);

        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
    }
    
    // ----------------------------------
    // CREAR
    
    public static CategoriaGasto crear(
    		String nombre,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    		) {
    	return new CategoriaGasto(
    			null, 
    			nombre, 
    			tipo, 
    			fechaCreacion
    	);
    }

    // ----------------------------------
    // RECONSTRUIR
    
    public static CategoriaGasto reconstruir(
    		Long id,
    		String nombre,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    		) {
    	return new CategoriaGasto(
    			id, 
    			nombre, 
    			tipo, 
    	fechaCreacion);
    }

    // ----------------------------------
    // MODIFICAR

    public void renombrar(String nuevoNombre) {
        validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
    }
    
    // ----------------------------------
    // VALIDACIONES
    
    public boolean esTipo(TipoObjeto tipo) {
        return this.tipo == tipo;
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw ValidationException.of("El nombre de la categoría es obligatorio");
        }

        if (nombre.length() > 50) {
            throw ValidationException.of("El nombre es demasiado largo");
        }
    }
}