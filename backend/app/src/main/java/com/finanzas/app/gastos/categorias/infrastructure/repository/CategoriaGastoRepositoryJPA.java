package com.finanzas.app.gastos.categorias.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finanzas.app.gastos.categorias.infrastructure.CategoriaGastoEntityJPA;
import com.finanzas.app.shared.domain.enums.TipoObjeto;

public interface CategoriaGastoRepositoryJPA extends JpaRepository<CategoriaGastoEntityJPA, Long>{

	List<CategoriaGastoEntityJPA> findAllByUsuarioIdOrderByNombreAsc(Long usuarioId);
	
	@Modifying
	@Query("""
		    DELETE FROM CategoriaGastoEntityJPA cg
		    WHERE cg.id = :categoriaId
		      AND cg.usuarioId = :usuarioId
		      AND cg.tipo = :tipoSistema
		""")
	int eliminar(
			@Param("categoriaId") Long categoriaId,
			@Param("usuarioId") Long usuarioId,
		    @Param("tipoSistema") TipoObjeto tipoSistema
			);

	@Query("""
		    SELECT cg
		    FROM CategoriaGastoEntityJPA cg
		    WHERE cg.usuarioId = :usuarioId
		        OR cg.tipo = :tipoSistema
		    ORDER BY cg.nombre ASC
		""")
		List<CategoriaGastoEntityJPA> listarTodos(
			@Param("usuarioId") Long usuarioId,
		    @Param("tipoSistema") TipoObjeto tipoSistema
			);

	@Query("""
		    SELECT cg
		    FROM CategoriaGastoEntityJPA cg
		    WHERE cg.id = :categoriaId
		      AND (cg.usuarioId = :usuarioId OR cg.tipo = :tipoSistema)
		    """)
		Optional<CategoriaGastoEntityJPA> buscarPorId(
			@Param("categoriaId") Long categoriaId,
		    @Param("usuarioId") Long usuarioId,
		    @Param("tipoSistema") TipoObjeto tipoSistema
			);

	@Query("""
		    SELECT CASE WHEN COUNT(cg) > 0 THEN true ELSE false END
		    FROM CategoriaGastoEntityJPA cg
		    WHERE cg.id = :categoriaId
		      AND (cg.usuarioId = :usuarioId OR cg.tipo = :tipoSistema)
		    """)
		boolean existePorId(
		    @Param("categoriaId") Long categoriaId,
		    @Param("usuarioId") Long usuarioId,
		    @Param("tipoSistema") TipoObjeto tipoSistema
		);
	
	@Query("""
		    SELECT CASE WHEN COUNT(cg) > 0 THEN true ELSE false END
		    FROM CategoriaGastoEntityJPA cg
		    WHERE cg.nombre = :nombre
		      AND (cg.usuarioId = :usuarioId OR cg.tipo = :tipoSistema)
		    """)
		boolean existePorNombre(
				@Param("nombre") String nombre,
			    @Param("usuarioId") Long usuarioId,
			    @Param("tipoSistema") TipoObjeto tipoSistema
			);
}
