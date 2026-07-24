package com.finanzas.app.gastos.infrastructure.respository.gasto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.infrastructure.entity.GastoEntityJPA;

@Repository
public interface GastoRepositoryJPA extends JpaRepository<GastoEntityJPA, Long>{
	
	List<GastoEntityJPA> findByCategoriaGastoId(Long categoriaGastoId);
	
	boolean existsByGastoRecurrenteId(Long gastoRecurrenteId);

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
