package com.finanzas.app.identity.infrastructure.entity;

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

/**
 * Entidad JPA que representa la tabla "usuarios" en la base de datos.
 *
 * Esta clase pertenece a la capa de infraestructura y es responsable
 * de mapear la estructura del dominio Usuario a una representación persistente.
 *
 * Responsabilidades:
 * - Persistir datos del usuario en la base de datos.
 * - Servir como modelo de mapeo ORM (JPA/Hibernate).
 *
 * Nota:
 * - No debe ser utilizada directamente en la capa de dominio.
 * - Se utiliza exclusivamente para persistencia y recuperación de datos.
 * 
 * Nota de arquitectura:
 * Esta entidad se mapea desde/hacia la entidad de dominio Usuario
 * mediante un mapper en la capa de infraestructura.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "usuarios")
public class UsuarioEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
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
        entity.email = email;
        entity.password = password;
        entity.activo = activo;
        entity.fechaCreacion = fechaCreacion;
        entity.fechaModificacion = fechaModificacion;
        entity.fechaCambioEstado = fechaCambioEstado;
        entity.fechaCambioPassword = fechaCambioPassword;
        entity.resetPasswordVersion = resetPasswordVersion;
    	return entity;
    }
    
}