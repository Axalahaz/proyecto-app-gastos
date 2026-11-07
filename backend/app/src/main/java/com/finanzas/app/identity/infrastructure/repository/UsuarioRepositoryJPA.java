package com.finanzas.app.identity.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finanzas.app.identity.infrastructure.UsuarioEntityJPA;

import java.util.Optional;

public interface UsuarioRepositoryJPA extends JpaRepository<UsuarioEntityJPA, Long> {

    Optional<UsuarioEntityJPA> findByEmail(String email);

    Optional<UsuarioEntityJPA> findById(Long id);
    
    @Modifying
    @Query("""
    		DELETE FROM UsuarioEntityJPA u
    		WHERE u.id = :usuarioId
    	""")
    int eliminarPorId(
    		@Param("id") Long usuarioId
    		);
}