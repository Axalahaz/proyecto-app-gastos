package com.finanzas.app.identity.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finanzas.app.identity.infrastructure.entity.UsuarioEntityJPA;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad UsuarioEntity.
 *
 * Proporciona acceso a operaciones de persistencia sobre la tabla "usuarios".
 *
 * Esta interfaz es parte de la capa de infraestructura y utiliza Spring Data JPA
 * para generar automáticamente las implementaciones de acceso a datos.
 *
 * Responsabilidades:
 * - Persistir y recuperar usuarios desde la base de datos.
 * - Proveer consultas derivadas por convención de nombres.
 *
 * Nota:
 * - No contiene lógica de negocio.
 * - Es una abstracción técnica utilizada por la implementación del repositorio de dominio.
 */

public interface UsuarioRepositoryJPA extends JpaRepository<UsuarioEntityJPA, Long> {

    Optional<UsuarioEntityJPA> findByEmail(String email);

    Optional<UsuarioEntityJPA> findById(Long id);

}