package com.finanzas.app.gastos.gastos.infrastructure.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.gastos.infrastructure.GastoEntityJPA;
import com.finanzas.app.gastos.recurrentes.domain.EstadoConf;

@Repository
public interface GastoRepositoryJPA extends JpaRepository<GastoEntityJPA, Long>{
	
	List<GastoEntityJPA> findByUsuarioId(Long usuarioId);

	Optional<GastoEntityJPA> findByIdAndUsuarioId(Long gastoId, Long usuarioId);
	
	boolean existsByConfGastoRecurrenteIdAndUsuarioId(Long confGastoRecurrenteId, Long usuarioId);

	@Query("""
		    SELECT COUNT(g) > 0
		    FROM GastoEntityJPA g
		    WHERE g.confGastoRecurrenteId = :confGastoRecurrenteId
		        AND g.estado = 'ACTIVO'
		        AND g.fechaCreacion >= :desde
		        AND g.fechaCreacion < :hasta
		        AND g.usuarioId = :usuarioId
		""")
	boolean existePagoActivoEnPeriodo(
			@Param("gastoRecurrenteId") Long confGastoRecurrenteId,
			@Param("desde") LocalDateTime desde,
			@Param("hasta") LocalDateTime hasta,
			@Param("usuarioId") Long usuarioId
			);
	
	@Modifying
	@Query("""
		    DELETE FROM GastoEntityJPA g
		    WHERE g.id = :gastoId
		      AND g.usuarioId = :usuarioId
		      AND g.estado = :estado
		""")
	int eliminar(
			@Param("gastoId") Long gastoId,
			@Param("usuarioId") Long usuarioId,
			@Param("estado") EstadoConf estado
			);
}
