package com.finanzas.app.gastos.infrastructure.respository.plantillaGastoRecurrente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.infrastructure.entity.PlantillaGastoRecurrenteEntityJPA;

@Repository
public interface PlantillaGastoRecurrenteRepositoryJPA extends JpaRepository<PlantillaGastoRecurrenteEntityJPA, Long>{
	
	boolean existsByDescripcion(String descripcion);

	boolean existsById(Long plantillaId);
	
}