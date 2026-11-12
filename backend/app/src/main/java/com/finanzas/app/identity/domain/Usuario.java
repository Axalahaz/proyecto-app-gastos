package com.finanzas.app.identity.domain;

import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.exceptionImpl.ForbiddenException;
import com.finanzas.app.shared.exception.exceptionImpl.ValidationException;

import lombok.Getter;

@Getter
public class Usuario {

    private Long id;
    private String nombreUsuario;
    private Email email;
    private String passwordHash;
    private boolean activo;
    private Fecha fechaCreacion;
    private Fecha fechaModificacion;
    private Fecha fechaCambioEstado;
    
    private Fecha fechaCambioPassword; 
    private Integer resetPasswordVersion; 

    // ----------------------------------------------------
    // CONTRUCTORES
    
    private Usuario(
    		Long id,
    		String nombreUsuario, 
    		Email email,
            String passwordHash,
            boolean activo,
            Fecha fechaCreacion,
            Fecha fechaModificacion,
            Fecha fechaCambioEstado,
            Fecha fechaCambioPassword,
    		Integer resetPasswordVersion
    		) {
    	
    	validarPasswordHash(passwordHash);
    	validarNombreUsuario(nombreUsuario);

		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.passwordHash = passwordHash;
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
    		String nombreUsuario, 
    		Email email, 
    		String passwordHash, 
    		Fecha fechaCreacion) {
    	return new Usuario(
    			null,
    			nombreUsuario, 
    			email, 
    			passwordHash, 
    			true,
    			fechaCreacion,
    			null,
    			null,
    			null,
    			0
    	);
    }
    
    // ----------------------------------------------------
    // RECONSTRUIR
    
    public static Usuario reconstruir(
    	    Long id,
    	    String nombreUsuario,
    	    Email email,
    	    String passwordHash,
    	    boolean activo,
    	    Fecha fechaCreacion,
    	    Fecha fechaModificacion,
    	    Fecha fechaCambioEstado,
    		Fecha fechaCambioPassword,
    		Integer resetPasswordVersion
    	) {
    	    return new Usuario(
    	    		id,
    	    		nombreUsuario,
    	    		email,
    	    		passwordHash,
    	    		activo,
    	    		fechaCreacion,
    	    		fechaModificacion,
    	    		fechaCambioEstado,
    	    		fechaCambioPassword,
    	    		resetPasswordVersion
    	    );
    	}
    

    // **********************************************************************************************
    // <<<<<<<< COMPORTAMIENTO DE DOMINIO >>>>>>>>>
    // **********************************************************************************************
    
    // ----------------------------------
    // ESTADO
    
    public void verificarEstadoActivo() {
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
    
    public void editar(
    		Email nuevoEmail,
    		String nuevoNombreUsuario,
    		Fecha ahora
    	) {
    	
    	if (nuevoEmail != null) actualizarEmail(nuevoEmail);
    	if (nuevoNombreUsuario != null) actualizarNombreUsuario(nuevoNombreUsuario);
    	
    	this.fechaModificacion = ahora;
    }
    
    public void actualizarEmail(Email nuevoEmail) {
    	this.email = nuevoEmail;
    }
    
    public void actualizarNombreUsuario(String nuevoNombreUsuario) {
    	validarNombreUsuario(nuevoNombreUsuario);
    	this.nombreUsuario = nuevoNombreUsuario;
    }

    
    // ----------------------------------------------------
    // CAMBIAR PASSWORD
    
    private void actualizarPassword(String nuevaPassword) {
        this.passwordHash = nuevaPassword;
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
    
    private void validarUsuarioActivo() {
        if (!this.activo) {
        	throw new ForbiddenException("El usuario esta inactivo");
        }
    }
    
    private void validarUsuarioInactivo() {
    	if (this.activo) {
    		throw new ForbiddenException("El usuario esta activo");
    	}
    }
    
    // ----------------------------------------------------
    // Validacion atributos
    
    private void validarNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw ValidationException.of("El nombre de usuario es inválido");
        }
        if (nombreUsuario.length() > 10) {
            throw ValidationException.of("El nombre de usuario debe tener Max 10 caracteres");
        }
        nombreUsuario = nombreUsuario.trim();
    }
    
    private void validarPasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw ValidationException.of("La contraseña es inválida");
        }
    }
}