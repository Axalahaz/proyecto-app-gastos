package com.finanzas.app.gastos.infrastructure.respository.categoriaGasto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finanzas.app.gastos.infrastructure.entity.CategoriaGastoEntityJPA;
import com.finanzas.app.shared.domain.model.TipoObjeto;

public interface CategoriaGastoRepositoryJPA extends JpaRepository<CategoriaGastoEntityJPA, Long>{
	
	@Modifying
	@Query("""
		    DELETE FROM CategoriaGastoEntityJPA c
		    WHERE c.usuarioId = :usuarioId
		""")
	void deleteAllByUsuarioId(@Param("usuarioId") Long usuarioId);
	
	@Query("""
		    SELECT c
		    FROM CategoriaGastoEntityJPA c
		    WHERE c.id = :categoriaId
		    AND (
		        c.usuario.id = :usuarioId
		        OR c.usuario IS NULL
		    )
		""")
	Optional<CategoriaGastoEntityJPA> buscarDisponible(
	        @Param("categoriaId") Long categoriaId,
	        @Param("usuarioId") Long usuarioId
	);
	
	List<CategoriaGastoEntityJPA> findByUsuarioId(Long usuarioId);

	List<CategoriaGastoEntityJPA> findByTipo(TipoObjeto tipo);
	
	Optional<CategoriaGastoEntityJPA> findByIdAndUsuarioId(Long categoriaId, Long usuarioId);

	Optional<CategoriaGastoEntityJPA> findByIdAndUsuarioIdIsNull(Long categoriaId);
	
	boolean existsByIdAndUsuarioId(Long categoriaId, Long usuarioId);
	
	// ADMIN
	Optional<CategoriaGastoEntityJPA>  findByIdAndTipo(Long categoriaId, TipoObjeto tipo);
}
