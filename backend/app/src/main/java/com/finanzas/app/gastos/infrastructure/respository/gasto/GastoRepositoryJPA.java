package com.finanzas.app.gastos.infrastructure.respository.gasto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.infrastructure.entity.GastoEntityJPA;

@Repository
public interface GastoRepositoryJPA extends JpaRepository<GastoEntityJPA, Long>{
	
	List<GastoEntityJPA> findByCategoriaGastoId(Long categoriaGastoId);

	@Query("""
		    SELECT g
		    FROM GastoEntityJPA g
		    WHERE g.categoriaGastoId = :categoriaGastoId
			    AND g.usuarioId = :usuarioId
		""")
	List<GastoEntityJPA> buscarPorCategoriaYUsuario(
			@Param("categoriaGastoId") Long categoriaGastoId,
			@Param("usuarioId") Long usuarioId);
	
	@Modifying
	@Query("""
		    DELETE FROM GastoEntityJPA g
		    WHERE g.usuarioId = :usuarioId
		""")
	void deleteAllByUsuarioId(@Param("usuarioId") Long usuarioId);
	
	@Query("""
		    SELECT g
		    FROM GastoEntityJPA g
		    WHERE g.id = :gastoId
			    AND g.usuarioId = :usuarioId
		""")
	Optional<GastoEntityJPA>  buscarPorIdYUsuario(
			@Param("gastoId") Long gastoId,
			@Param("usuarioId") Long usuarioId);
	
	@Query("""
		    SELECT COUNT(g) > 0
		    FROM GastoEntityJPA g
		    WHERE g.gastoRecurrenteId = :gastoRecurrenteId
		        AND g.estado = 'ACTIVO'
		        AND g.fechaCreacion >= :desde
		        AND g.fechaCreacion < :hasta
		""")
	boolean existePagoActivoEnPeriodo(
			@Param("gastoRecurrenteId") Long gastoRecurrenteId,
			@Param("desde") LocalDateTime desde,
			@Param("hasta") LocalDateTime hasta);
}
