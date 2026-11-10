package com.finanzas.app.gastos.categorias.domain;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.enums.TipoObjeto;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.UsuarioId;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

import lombok.Getter;

@Getter
public class CategoriaGasto {

    private Long id;
    private UsuarioId usuarioId;
    private String nombre;
    private TipoObjeto tipo;
    private Fecha fechaCreacion;
    
    // ----------------------------------
    // CONSTRUCTOR

    private CategoriaGasto(
    		Long id,
    		UsuarioId usuarioId,
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
    		UsuarioId usuarioId,
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
    		UsuarioId usuarioId,
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
    
    public void validarEsTipoUsuario() {
        if(this.tipo == TipoObjeto.SISTEMA) {
    		throw new ConflictException(
    				"No se puede eliminar una Categoria de Gasto propia del sistema");
    	}
    }
    
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw ValidationException.of("El nombre de la categoría es obligatorio");
        }

        if (nombre.length() > 50) {
            throw ValidationException.of("El nombre es demasiado largo Max. 50 Caracteres");
        }
        
        nombre = nombre.trim();
    }
    
    //****************
    //!!!!!!!! VALIDACION DE EN EL DIA Y DENTRO DE UNA HORA    
    
    public void validarEliminacion(LocalDateTime tiempoActual) {
    	validarEsTipoUsuario();
    	LocalDateTime fechaCreacion = this.fechaCreacion.value();
    	
    	if (tiempoActual.isBefore(fechaCreacion)
                || tiempoActual.isAfter(fechaCreacion.plusHours(1))) {
            throw new ConflictException(
                    "Solo se puede eliminar categoria dentro de la primera hora desde su creación"
            );
        }
    }
}