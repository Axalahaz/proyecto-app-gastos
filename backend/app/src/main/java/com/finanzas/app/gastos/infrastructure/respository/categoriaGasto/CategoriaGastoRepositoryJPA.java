package com.finanzas.app.gastos.infrastructure.respository.categoriaGasto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finanzas.app.gastos.infrastructure.entity.CategoriaGastoEntityJPA;
import com.finanzas.app.shared.domain.model.TipoObjeto;

public interface CategoriaGastoRepositoryJPA extends JpaRepository<CategoriaGastoEntityJPA, Long>{
	
	List<CategoriaGastoEntityJPA> findAllByOrderByNombreAsc();

	List<CategoriaGastoEntityJPA> findByTipoOrderByNombreAsc(TipoObjeto tipo);
	
	Optional<CategoriaGastoEntityJPA> findById(Long categoriaId);

	boolean existsByNombre(String nombre);

	boolean existsById(Long categoriaId);
	
}
