package com.finanzas.app.gastos.infrastructure.respository.gasto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.infrastructure.entity.GastoEntityJPA;
import com.finanzas.app.shared.domain.model.EstadoMovimiento;

// READ MODEL / REPORTING

@Repository
public interface GastoQueryRepositoryJPA extends JpaRepository<GastoEntityJPA, Long>{
	
	// ---------------------------------------------------------------
	// TOTAL PAGOS RECURRENTES POR FECHA
	
	@Query("""
			SELECT COALESCE(SUM(g.monto), 0)
			FROM GastoEntityJPA g
			JOIN GastoRecurrenteEntityJPA gr
		        ON gr.id = g.gastoRecurrenteId
			WHERE g.usuarioId = :usuarioId
				AND g.estado IN :estados
				AND g.gastoRecurrenteId IS NOT NULL
				AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
				AND gr.activo IN :recurrente
			""")
	BigDecimal consultarTotalPagos(
			@Param("usuarioId") Long usuarioId, 
			@Param("fechaInicio") LocalDateTime fechaInicio,
			@Param("fechafin") LocalDateTime fechafin,
			@Param("estados") List<EstadoMovimiento> estados,
			@Param("recurrente") List<Boolean> recurrente
			);
	
	// ---------------------------------------------------------------
	// DETALLE DE PAGOS RECURRENTES
	
	@Query("""
		    SELECT g.descripcion, g.monto, c.nombre, g.fechaCreacion
		    FROM GastoEntityJPA g
		    JOIN CategoriaGastoEntityJPA c
		        ON c.id = g.categoriaGastoId
		    JOIN GastoRecurrenteEntityJPA gr
		        ON gr.id = g.gastoRecurrenteId
		    WHERE g.usuarioId = :usuarioId
			    AND g.estado IN :estados
			    AND g.gastoRecurrenteId IS NOT NULL
			    AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
				AND gr.activo IN :recurrente
			ORDER BY 
				g.fechaCreacion DESC
		""")
	List<Object[]> consultarDetallePagosRealizados(
			@Param("usuarioId") Long usuarioId, 
			@Param("fechaInicio") LocalDateTime fechaInicio,
			@Param("fechafin") LocalDateTime fechafin,
			@Param("estados") List<EstadoMovimiento> estados,
			@Param("recurrente") List<Boolean> recurrente
			);
	
	// ---------------------------------------------------------------
	// TOTAL GASTOS POR FECHA
	
	@Query("""
		    SELECT COALESCE(SUM(g.monto), 0)
		    FROM GastoEntityJPA g
		    WHERE g.usuarioId = :usuarioId
			    AND g.estado IN :estados
			    AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
		""")
	BigDecimal consultarTotalGastos(
			@Param("usuarioId") Long usuarioId, 
			@Param("fechaInicio") LocalDateTime fechaInicio,
		    @Param("fechafin") LocalDateTime fechafin,
			@Param("estados") List<EstadoMovimiento> estados);
	

	// ---------------------------------------------------------------
	// TOTAL GASTOS POR CATEGORIA
	
	@Query("""
		    SELECT c.nombre, COALESCE(SUM(g.monto), 0)
		    FROM GastoEntityJPA g
		    JOIN CategoriaGastoEntityJPA c
		        ON c.id = g.categoriaGastoId
		    WHERE g.usuarioId = :usuarioId
			    AND g.estado IN :estados
			    AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
			GROUP BY c.id, c.nombre
			ORDER BY c.nombre ASC
		""")
	List<Object[]> consultarTotalesPorCategoria(
			@Param("usuarioId") Long usuarioId, 
			@Param("fechaInicio") LocalDateTime fechaInicio,
		    @Param("fechafin") LocalDateTime fechafin,
		    @Param("estados") List<EstadoMovimiento> estados);
}
