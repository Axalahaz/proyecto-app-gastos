package com.finanzas.app.gastos.infrastructure.respository.gastoRecurrente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.infrastructure.entity.GastoRecurrenteEntityJPA;

@Repository
public interface GastoRecurrenteRepositoryJPA extends JpaRepository<GastoRecurrenteEntityJPA, Long>{
	
	boolean existsByDescripcionAndCategoriaGastoId(String descripcion, Long categoriaGastoId);
	
	@Modifying
	@Query("""
		    DELETE FROM GastoRecurrenteEntityJPA g
		    WHERE g.categoriaGastoId IN (
		        SELECT c.id
		        FROM CategoriaGastoEntityJPA c
		        WHERE c.usuarioId = :usuarioId
		    )
		""")
	void deleteAllByUsuarioId(@Param("usuarioId") Long usuarioId);

	List<GastoRecurrenteEntityJPA>  findByUsuarioIdOrUsuarioIdNull(
			@Param("usuarioId") Long usuarioId);
	
	Optional<GastoRecurrenteEntityJPA>  findByIdAndUsuarioId(
			Long gastoRecurrenteId,
			Long usuarioId);
	
	@Query("""
		    SELECT g
		    FROM GastoRecurrenteEntityJPA g
		    JOIN CategoriaGastoEntityJPA c
		        ON c.id = g.categoriaGastoId
		    WHERE c.id = :categoriaGastoId
			    AND (c.usuarioId = :usuarioId
			    OR c.usuarioId IS NULL)
		""")
	List<GastoRecurrenteEntityJPA> listarPorCategoria(
			@Param("usuarioId") Long usuarioId,
			@Param("categoriaGastoId") Long categoriaGastoId);
	
}
