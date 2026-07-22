package com.finanzas.app.gastos.infrastructure.respository.plantillaGastoRecurrente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.infrastructure.entity.PlantillaGastoRecurrenteEntityJPA;

@Repository
public interface PlantillaGastoRecurrenteRepositoryJPA extends JpaRepository<PlantillaGastoRecurrenteEntityJPA, Long>{
	
	boolean existsByDescripcion(String descripcion);
	
	@Query("""
		    SELECT g
		    FROM GastoRecurrenteEntityJPA g
		    JOIN CategoriaGastoEntityJPA c
		        ON c.id = g.categoriaGastoId
		    WHERE c.id = :categoriaGastoId
			    AND (c.usuarioId = :usuarioId
			    OR c.usuarioId IS NULL)
		""")
	List<PlantillaGastoRecurrenteEntityJPA> listarPorCategoria(
			@Param("usuarioId") Long usuarioId,
			@Param("categoriaGastoId") Long categoriaGastoId);
	
}
