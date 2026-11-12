package com.finanzas.app.identity.infrastructure;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "usuarios")
public class UsuarioEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreUsuario;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    @Column(nullable = false)
    private boolean activo;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_modificacion", nullable = true)
    private LocalDateTime fechaModificacion;
    
    @Column(name = "fecha_cambio_estado", nullable = true)
    private LocalDateTime fechaCambioEstado;
    
    @Column(name = "fecha_cambio_password", nullable = true)
    private LocalDateTime fechaCambioPassword;
    
    @Column(name = "reset_password_version", nullable = true)
    private Integer resetPasswordVersion; 
    
    /**
     * ----------------------------------------------------
     * OF
     * ----------------------------------------------------
     * Factory method: crea un nuevo UsuarioEntity para persistir en BD.
     *
     */
    
    public static UsuarioEntityJPA of(
    		Long id,
    		String nombreUsuario,
    	    String email,
    	    String password,
    	    boolean activo,
    	    LocalDateTime fechaCreacion,
    	    LocalDateTime fechaModificacion,
    	    LocalDateTime fechaCambioEstado,
    	    LocalDateTime fechaCambioPassword,
    	    Integer resetPasswordVersion
    	    ) {
    	UsuarioEntityJPA entity = new UsuarioEntityJPA();
    	
    	entity.id = id;
    	entity.nombreUsuario = nombreUsuario;
        entity.email = email;
        entity.passwordHash = password;
        entity.activo = activo;
        entity.fechaCreacion = fechaCreacion;
        entity.fechaModificacion = fechaModificacion;
        entity.fechaCambioEstado = fechaCambioEstado;
        entity.fechaCambioPassword = fechaCambioPassword;
        entity.resetPasswordVersion = resetPasswordVersion;
    	return entity;
    }
}