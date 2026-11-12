package com.finanzas.app.gastos.gastos.infrastructure.respository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.gastos.infrastructure.GastoEntityJPA;
import com.finanzas.app.gastos.recurrentes.domain.EstadoConf;
import com.finanzas.app.shared.domain.enums.EstadoMovimiento;

// READ MODEL / REPORTING

//@Repository
@NoRepositoryBean
public interface GastoQueryRepositoryJPA extends JpaRepository<GastoEntityJPA, Long>{
	
	// ---------------------------------------------------------------
	// TOTAL PAGOS RECURRENTES POR FECHA
	
	@Query("""
			SELECT COALESCE(SUM(g.monto), 0)
			FROM GastoEntityJPA g
			JOIN ConfGastoRecurrenteEntityJPA cgr
		        ON cgr.id = g.confGastoRecurrenteId
			WHERE  g.estado IN :estadosMovimiento
				AND g.confGastoRecurrenteId IS NOT NULL
				AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
				AND cgr.estado IN :estadosRecurrente
			""")
	BigDecimal consultarTotalPagos(
			@Param("fechaInicio") LocalDateTime fechaInicio,
			@Param("fechafin") LocalDateTime fechafin,
			@Param("estadosMovimiento") List<EstadoMovimiento> estadosMovimiento,
			@Param("estadosRecurrente") List<EstadoConf> estadosRecurrente
			);
	
	// ---------------------------------------------------------------
	// DETALLE DE PAGOS RECURRENTES
	
	@Query("""
		    SELECT g.descripcion, g.monto, c.nombre, g.fechaCreacion
		    FROM GastoEntityJPA g
		    JOIN CategoriaGastoEntityJPA c
		        ON c.id = g.categoriaGastoId
		    JOIN ConfGastoRecurrenteEntityJPA cgr
		        ON gr.id = g.confGastoRecurrenteId
		    WHERE g.estado IN :estadosMovimiento
			    AND g.confGastoRecurrenteId IS NOT NULL
			    AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
				AND cgr.estado IN :estadosRecurrente
			ORDER BY 
				g.fechaCreacion DESC
		""")
	List<Object[]> consultarDetallePagosRealizados(
			@Param("fechaInicio") LocalDateTime fechaInicio,
			@Param("fechafin") LocalDateTime fechafin,
			@Param("estadosMovimiento") List<EstadoMovimiento> estadosMovimiento,
			@Param("estadosRecurrente") List<EstadoConf> estadosRecurrente
			);
	
	// ---------------------------------------------------------------
	// TOTAL GASTOS POR FECHA
	
	@Query("""
		    SELECT COALESCE(SUM(g.monto), 0)
		    FROM GastoEntityJPA g
		    WHERE g.estado IN :estadosMovimiento
			    AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
		""")
	BigDecimal consultarTotalGastos(
			@Param("fechaInicio") LocalDateTime fechaInicio,
		    @Param("fechafin") LocalDateTime fechafin,
			@Param("estadosMovimiento") List<EstadoMovimiento> estadosMovimiento);
	

	// ---------------------------------------------------------------
	// TOTAL GASTOS POR CATEGORIA
	
	@Query("""
		    SELECT c.nombre, COALESCE(SUM(g.monto), 0)
		    FROM GastoEntityJPA g
		    JOIN CategoriaGastoEntityJPA c
		        ON c.id = g.categoriaGastoId
		    WHERE g.estado IN :estadosMovimiento
			    AND g.fechaCreacion >= :fechaInicio
				AND g.fechaCreacion < :fechafin
			GROUP BY c.id, c.nombre
			ORDER BY c.nombre ASC
		""")
	List<Object[]> consultarTotalesPorCategoria(
			@Param("fechaInicio") LocalDateTime fechaInicio,
		    @Param("fechafin") LocalDateTime fechafin,
		    @Param("estadosMovimiento") List<EstadoMovimiento> estadosMovimiento);
}
