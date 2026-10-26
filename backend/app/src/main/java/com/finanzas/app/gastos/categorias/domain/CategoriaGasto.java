package com.finanzas.app.gastos.categorias.domain;

import com.finanzas.app.shared.domain.enums.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

import lombok.Getter;

@Getter
public class CategoriaGasto {

    private Long id;
    private Long usuarioId;
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
        
    	validarUsuario(usuarioId);
        validarNombre(nombre);
        validarFechaCreacion(fechaCreacion);

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
    	if (nuevoNombre == null) {
            return;
        }

        if (this.nombre.equals(nuevoNombre)) {
            return;
        }
        validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
    }
    
    // ----------------------------------
    // VALIDACIONES
    
    public boolean esTipoSistema() {
        return this.tipo == TipoObjeto.SISTEMA;
    }
    
    private void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw ValidationException.of(
                "El usuario es obligatorio");
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
    
    private void validarFechaCreacion(Fecha fechaCreacion) {
        if (fechaCreacion == null) {
            throw ValidationException.of(
                "La fecha de creacion es obligatoria");
        }
    }
}