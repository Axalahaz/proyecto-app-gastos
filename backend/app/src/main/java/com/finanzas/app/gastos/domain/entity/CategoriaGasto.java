package com.finanzas.app.gastos.domain.entity;

import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

@Getter
public class CategoriaGasto {

    private Long id;
    private Long usuarioId; // puede ser NULL
    private String nombre;
    private TipoObjeto tipo;
    private Fecha fechaCreacion;
    
    // ----------------------------------
    // CONSTRUCTOR

    private CategoriaGasto(
    		Long id,
    		Long usuarioId, 
    		String nombre,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    		) {
        
        validarNombre(nombre);

        this.id = id;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
    }
    
    // ----------------------------------
    // CREAR
    
    public static CategoriaGasto crear(
    		Long usuarioId, 
    		String nombre,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    		) {
    	validarUsuario(usuarioId);
    	return new CategoriaGasto(
    			null, 
    			usuarioId, 
    			nombre, 
    			tipo, 
    			fechaCreacion
    	);
    }

    // ----------------------------------
    // RECONSTRUIR
    
    public static CategoriaGasto reconstruir(
    		Long id,
    		Long usuarioId, 
    		String nombre,
    		TipoObjeto tipo,
    		Fecha fechaCreacion
    		) {
    	return new CategoriaGasto(
    			id, 
    			usuarioId, 
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
    
    private static void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw ValidationException.of("La categoría debe pertenecer a un usuario");
        }
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