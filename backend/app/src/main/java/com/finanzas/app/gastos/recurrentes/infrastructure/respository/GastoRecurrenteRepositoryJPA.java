package com.finanzas.app.gastos.recurrentes.infrastructure.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.recurrentes.infrastructure.GastoRecurrenteEntityJPA;

@Repository
public interface GastoRecurrenteRepositoryJPA extends JpaRepository<GastoRecurrenteEntityJPA, Long>{
	
	boolean existsByDescripcion(String descripcion);
	
	List<GastoRecurrenteEntityJPA> findByActivo(boolean activo);
	
}
