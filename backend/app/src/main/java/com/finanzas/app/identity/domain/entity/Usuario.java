package com.finanzas.app.identity.domain.entity;

import com.finanzas.app.identity.application.exception.UsuarioInactivoException;
import com.finanzas.app.identity.application.exception.UsuarioYaActivoException;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.Getter;

/**
 * Aggregate Root del dominio que representa un Usuario.
 *
 * Es la entidad principal del agregado de usuario y el único punto de entrada
 * para modificar su estado interno.
 *
 * Responsabilidades:
 * - Mantener las invariantes del usuario.
 * - Controlar su ciclo de vida (creación, activación, desactivación).
 * - Proveer comportamiento de dominio coherente.
 *
 * Reglas de dominio:
 * - El nombre, email y contraseña son obligatorios.
 * - Un usuario se crea activo por defecto.
 * - La fecha de creación se asigna automáticamente.
 *
 * Persistencia:
 * - Esta clase pertenece al dominio puro.
 * - No debe contener anotaciones de frameworks (JPA, Hibernate, etc).
 * - El mapeo a base de datos se realiza en la capa de infraestructura.
 */

@Getter
public class Usuario {

    private Long id;
    private String email;
    private String password;
    private boolean activo;
    private Fecha fechaCreacion;
    private Fecha fechaModificacion;
    private Fecha fechaCambioEstado;
    
    private Fecha fechaCambioPassword; 
    private Integer resetPasswordVersion; 

    // ----------------------------------------------------
    // CONTRUCTORES
    
    private Usuario(
    		String email, 
    		String password, 
    		Fecha fechaCreacion
    		) {
        this.email = email;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
        
        validarEmail(email);
        validarPassword(password);

        this.activo = true;
        this.fechaModificacion = null;
        this.fechaCambioEstado = null;
        this.fechaCambioPassword = null;
        this.resetPasswordVersion = 0;
    }
    
    private Usuario(Long id,
            String email,
            String password,
            boolean activo,
            Fecha fechaCreacion,
            Fecha fechaModificacion,
            Fecha fechaCambioEstado,
            Fecha fechaCambioPassword,
    		Integer resetPasswordVersion
    		) {
    	
        validarEmail(email);
        validarPassword(password);

		this.id = id;
		this.email = email;
		this.password = password;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
        this.fechaCambioEstado = fechaCambioEstado;
        this.fechaCambioPassword = fechaCambioPassword;
        this.resetPasswordVersion = resetPasswordVersion;
		
	}
    
    // **********************************************************************************************
    // <<<<<<<< METODOS DE CREACION >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------------------------
    // CREAR (nuevos usuarios)
    
    public static Usuario crear(
    		String email, 
    		String password, 
    		Fecha fechaCreacion) {
    	return new Usuario( email, password, fechaCreacion);
    }
    
    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static Usuario reconstruir(
    	    Long id,
    	    String email,
    	    String password,
    	    boolean activo,
    	    Fecha fechaCreacion,
    	    Fecha fechaModificacion,
    	    Fecha fechaCambioEstado,
    		Fecha fechaCambioPassword,
    		Integer resetPasswordVersion
    	) {
    	    return new Usuario(id, email, password, activo, fechaCreacion, 
    	    		fechaModificacion, fechaCambioEstado, fechaCambioPassword, resetPasswordVersion);
    	}
    

    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------
    // ESTADO
    
    public void verificarActivo() {
    	validarUsuarioActivo();
    }
    
    public void desactivar(Fecha ahora) {
    	validarUsuarioActivo();
    	
        this.activo = false;
        this.fechaCambioEstado = ahora;
    }
    
    public void reactivar(Fecha ahora) {
    	validarUsuarioInactivo();
    	
        this.activo = true;
        this.fechaCambioEstado = ahora;
    }
    
    public boolean puedeEliminarse(Fecha fechaLimite) {
        return !this.activo &&
               this.fechaCambioEstado != null &&
               this.fechaCambioEstado.esAnteriorA(fechaLimite);
    }
    
    // ----------------------------------------------------
    // ACTUALIZAR
    
    public void actualizarEmail(String nuevoEmail) {
    	validarEmail(nuevoEmail);
        this.email = nuevoEmail;
    }
    
    public void actualizarDatos(String nuevoEmail, Fecha ahora) {

    	if(nuevoEmail != null) actualizarEmail(nuevoEmail);
    	
        this.fechaModificacion = ahora;
    }
    
    // ----------------------------------------------------
    // CAMBIAR PASSWORD
    
    private void actualizarPassword(String nuevaPassword) {
        this.password = nuevaPassword;
    }
    
    public void cambiarPassword(String nuevaPassword, Fecha ahora) {

    	actualizarPassword(nuevaPassword);
    	
        this.fechaCambioPassword = ahora;
    }
    
    public void incrementarResetPasswordVersion() {
        this.resetPasswordVersion++;
    }
    
    // ----------------------------------
    // METODOS PRIVADOS 
    // ----------------------------------
    
    // ----------------------------------------------------
    // Validacion
    
    private void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw ValidationException.of("El email es inválido");
        }
    }
    
    private void validarPassword(String password) {
        if (password == null || password.isBlank()) {
            throw ValidationException.of("La contraseña es inválida");
        }
    }
    
    private void validarUsuarioActivo() {
        if (!this.activo) {
            throw new UsuarioInactivoException();
        }
    }
    
    private void validarUsuarioInactivo() {
    	if (this.activo) {
    		throw new UsuarioYaActivoException();
    	}
    }
}