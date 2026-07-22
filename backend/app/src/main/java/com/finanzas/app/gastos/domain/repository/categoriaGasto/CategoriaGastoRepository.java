package com.finanzas.app.gastos.domain.repository.categoriaGasto;

import java.util.List;
import java.util.Optional;

import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.shared.domain.model.TipoObjeto;

public interface CategoriaGastoRepository {
	
	CategoriaGasto guardar(CategoriaGasto categoria);
	
	void eliminar(Long categoriaId);

	void eliminarTodos(Long usuarioId);
	
	boolean existePorIdYUsuarioId(Long categoriaId, Long usuarioId);
	
	Optional<CategoriaGasto> buscarPorIdYTipo(Long categoriaId, TipoObjeto tipo);

	Optional<CategoriaGasto> buscarPorIdYUsuario(Long categoriaId, Long usuarioId);
	
	Optional<CategoriaGasto> buscarDisponible(Long categoriaId, Long usuarioId);

	List<CategoriaGasto> listarPorUsuario(Long usuarioId);
	
	List<CategoriaGasto> listarPorTipo(TipoObjeto tipo);
}
