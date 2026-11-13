package com.finanzas.app.gastos.recurrentes.infrastructure.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.recurrentes.infrastructure.ConfGastoRecurrenteEntityJPA;

@Repository
public interface ConfGastoRecurrenteRepositoryJPA extends JpaRepository<ConfGastoRecurrenteEntityJPA, Long>{
	
	List<ConfGastoRecurrenteEntityJPA> findAllByUsuarioIdOrderByDescripcionAsc(Long usuarioId);
	
	@Modifying
	@Query("""
		    DELETE FROM ConfGastoRecurrenteEntityJPA cgr
		    WHERE cgr.id = :confGastoRecurrenteId
		      AND cgr.usuarioId = :usuarioId
		""")
	int eliminar(
			@Param("confGastoRecurrenteId") Long confGastoRecurrenteId,
			@Param("usuarioId") Long usuarioId
			);
	
	@Query("""
		    SELECT cgr
		    FROM ConfGastoRecurrenteEntityJPA cgr
		    WHERE cgr.id = :confGastoRecurrenteId
			      AND cgr.usuarioId = :usuarioId
	    """)
	Optional<ConfGastoRecurrenteEntityJPA> buscarPorId(
		@Param("confGastoRecurrenteId") Long confGastoRecurrenteId,
	    @Param("usuarioId") Long usuarioId
		);
	
	@Query("""
		    SELECT CASE WHEN COUNT(cgr) > 0 THEN true ELSE false END
		    FROM ConfGastoRecurrenteEntityJPA cgr
		    WHERE cgr.id = :confGastoRecurrenteId
		      AND cgr.usuarioId = :usuarioId
		    """)
	boolean existePorId(
	    @Param("confGastoRecurrenteId") Long confGastoRecurrenteId,
	    @Param("usuarioId") Long usuarioId
	);
	
	@Query("""
		    SELECT CASE WHEN COUNT(cgr) > 0 THEN true ELSE false END
		    FROM ConfGastoRecurrenteEntityJPA cgr
		    WHERE cgr.descripcion = :descripcion
		      AND cgr.usuarioId = :usuarioId
		    """)
	boolean existePorDescripcion(
			@Param("descripcion") String descripcion,
		    @Param("usuarioId") Long usuarioId
		);
	
	// ---------------------------------------
	// ELIMINACION DE DATOS AL BORRAR USUARIO

	@Modifying(
		    flushAutomatically = true,
		    clearAutomatically = true
		)
	@Query("""
		    DELETE FROM ConfGastoRecurrenteEntityJPA cgr
		    WHERE cgr.usuarioId = :usuarioId
		""")
	int eliminarDatosDeUsuario(
			@Param("usuarioId") Long usuarioId
			);
	
	// ---------------------------------------
}
