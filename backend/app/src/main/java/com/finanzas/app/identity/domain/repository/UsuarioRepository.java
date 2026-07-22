package com.finanzas.app.identity.domain.repository;

import java.util.Optional;

import com.finanzas.app.identity.domain.entity.Usuario;

/**
 * Repositorio del dominio para la entidad Usuario.
 *
 * Define el contrato para la persistencia y recuperación de usuarios,
 * sin exponer detalles de implementación (base de datos, JPA, etc).
 *
 * Responsabilidades:
 * - Persistir usuarios del dominio.
 * - Recuperar usuarios según criterios de búsqueda.
 *
 * Notas:
 * - Esta interfaz pertenece al dominio.
 * - La implementación concreta se encuentra en la capa de infraestructura.
 * - No debe contener lógica de negocio, solo operaciones de acceso a datos.
 */

public interface UsuarioRepository {

	Usuario guardar(Usuario usuario);

	void eliminar(Long id);
    
    Optional<Usuario> buscarPorEmail(String email);
    
    Optional<Usuario> buscarPorId(Long id);
}