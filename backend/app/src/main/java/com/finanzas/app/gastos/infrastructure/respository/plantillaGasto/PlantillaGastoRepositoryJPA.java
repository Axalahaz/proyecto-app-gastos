package com.finanzas.app.gastos.infrastructure.respository.plantillaGasto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.infrastructure.entity.PlantillaGastoEntityJPA;
import com.finanzas.app.shared.domain.model.TipoObjeto;


@Repository
public interface PlantillaGastoRepositoryJPA extends JpaRepository<PlantillaGastoEntityJPA, Long>{
	
	List<PlantillaGastoEntityJPA> findAllByOrderByNombreAsc();
	
	List<PlantillaGastoEntityJPA> findByTipoOrderByNombreAsc(TipoObjeto tipo);

	boolean existsByDescripcionAndCategoriaGastoId(
		    String descripcion, Long categoriaGastoId);;

	boolean existsByCategoriaGastoId(Long categoriaGastoId);

	boolean existsById(Long plantillaId);

	
}