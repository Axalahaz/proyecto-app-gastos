package com.finanzas.app.gastos.categorias.domain;

import java.util.List;
import java.util.Optional;

import com.finanzas.app.shared.domain.model.TipoObjeto;

public interface CategoriaGastoRepository {
	
	CategoriaGasto guardar(CategoriaGasto categoria);
	
	boolean existePorNombre(String nombre);

	boolean existePorId(Long categoriaId);

	void eliminar(Long categoriaId);

	Optional<CategoriaGasto> buscarPorId(Long categoriaId);
	
	List<CategoriaGasto> listarAll();
	
	List<CategoriaGasto> listarPorTipo(TipoObjeto tipo);
}
