package com.finanzas.app.gastos.categorias.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finanzas.app.gastos.categorias.infrastructure.CategoriaGastoEntityJPA;
import com.finanzas.app.shared.domain.model.TipoObjeto;

public interface CategoriaGastoRepositoryJPA extends JpaRepository<CategoriaGastoEntityJPA, Long>{
	
	List<CategoriaGastoEntityJPA> findAllByOrderByNombreAsc();

	List<CategoriaGastoEntityJPA> findByTipoOrderByNombreAsc(TipoObjeto tipo);
	
	Optional<CategoriaGastoEntityJPA> findById(Long categoriaId);

	boolean existsByNombre(String nombre);

	boolean existsById(Long categoriaId);
	
}
